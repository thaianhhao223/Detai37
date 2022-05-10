package com.example.detai37.service;

import com.example.detai37.base.request.BasePageAndSortRequest;
import com.example.detai37.common.BillStatus;
import com.example.detai37.entity.*;
import com.example.detai37.model.ProductSale;
import com.example.detai37.model.ProductSaleId;
import com.example.detai37.repository.BillRepository;
import com.example.detai37.request.bill.CreateBillRequest;
import com.example.detai37.request.bill.SolvePaymentBillRequest;
import com.example.detai37.request.bill.UpdateBillRequest;
import com.example.detai37.request.product.UpdateStockProductRequest;
import com.example.detai37.ultis.PageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    private BillRepository billRepository;
    private ProductService productService;
    private CustomerService customerService;
    private SaleStaffService saleStaffService;

    public BillService(BillRepository billRepository, ProductService productService, CustomerService customerService, SaleStaffService saleStaffService) {
        this.billRepository = billRepository;
        this.productService = productService;
        this.customerService = customerService;
        this.saleStaffService = saleStaffService;
    }


    public Page<Bill> getAllBillWithPage(BasePageAndSortRequest pageAndSortRequest){
            Pageable pageable = PageableUtils.convertPageableAndSort(pageAndSortRequest.getPageNumber(), pageAndSortRequest.getPageSize(), pageAndSortRequest.getSort());
            Page<Bill> billPage = billRepository.findAll(pageable);
            return billPage;
    }

    public List<Bill> getAllBill(){
        List<Bill> list = billRepository.findAll();
        return list;
    }

    public Bill findBillById(String id){

        Optional<Bill> billOptional = billRepository.findById(id);
        Bill bill  = new Bill();
        if (billOptional != null){
            bill = billOptional.get();
        }
        return bill;
    }

    public Bill saveBill(CreateBillRequest createBillRequest){
        Bill newBill = new Bill();
        Customer customer = customerService.findCustomerById(createBillRequest.getCustomerId());
        SaleStaff saleStaff = saleStaffService.findSaleStaffById(createBillRequest.getSaleStaffId());
        List<ProductSale> productList = new ArrayList<>();
        for(ProductSaleId productId: createBillRequest.getProductSaleIdList()){
            Product product = productService.findProductById(productId.getProductId());
            ProductSale productSale = ProductSale.builder()
                    .product(product)
                    .quantity(productId.getQuantity())
                    .build();
            productList.add(productSale);
        }
        Double price = chargeTotal(newBill);
        newBill.setCustomer(customer);
        newBill.setSaleStaff(saleStaff);
        newBill.setProductList(productList);
        newBill.setDateSale(new Date());
        newBill.setDateSale(createBillRequest.getDateDelivery());
        newBill.setTotalPrice(price);
        newBill.setPaymentType(createBillRequest.getPaymentType());
        newBill.setStatus(BillStatus.BILL_UNPAID);
        Bill result = billRepository.save(newBill);
        return result;
    }

    public Bill updateBill(UpdateBillRequest updateBillRequest){
        Bill newBill = billRepository.getById(updateBillRequest.getBillId());
        Customer customer = customerService.findCustomerById(updateBillRequest.getCustomerId());
        SaleStaff saleStaff = saleStaffService.findSaleStaffById(updateBillRequest.getSaleStaffId());
        List<ProductSale> productList = new ArrayList<>();
        for(ProductSaleId productId: updateBillRequest.getProductSaleIdList()){
            Product product = productService.findProductById(productId.getProductId());
            ProductSale productSale = ProductSale.builder()
                    .product(product)
                    .quantity(productId.getQuantity())
                    .build();
            productList.add(productSale);
        }
        Double price = chargeTotal(newBill);
        newBill.setCustomer(customer);
        newBill.setSaleStaff(saleStaff);
        newBill.setProductList(productList);
        newBill.setDateSale(new Date());
        newBill.setDateSale(updateBillRequest.getDateDelivery());
        newBill.setTotalPrice(price);
        newBill.setPaymentType(updateBillRequest.getPaymentType());
        Bill result = billRepository.save(newBill);
        calculateQuantity(result);
        return result;
    }

    public Bill solvePayment(SolvePaymentBillRequest solvePaymentBillRequest){
        Bill bill = billRepository.getById(solvePaymentBillRequest.getBillId());
        if(solvePaymentBillRequest.getIsSolvePayment()){
            bill.setPaymentType(bill.getPaymentType());
            bill.setStatus(BillStatus.BILL_PAID);
        }
        bill.setPaymentType(solvePaymentBillRequest.getPaymentType());
        return bill;
    }

    public Double chargeTotal(Bill bill){
        Double total = 0d;
        for(ProductSale product: bill.getProductList()){
            Double sum = (product.getProduct().getPrice() * product.getQuantity());
            Double discount =  sum * product.getProduct().getPercentDiscount();
            total += sum - discount;
        }
        return total;
    }

    public void calculateQuantity(Bill bill){
        for(ProductSale product: bill.getProductList()){
            UpdateStockProductRequest updateStockProductRequest = UpdateStockProductRequest.builder()
                    .productId(product.getProduct().getId())
                    .stockHadSolve(product.getQuantity())
                    .build();
            productService.updateStockProduct(updateStockProductRequest);
        }
    }
}
