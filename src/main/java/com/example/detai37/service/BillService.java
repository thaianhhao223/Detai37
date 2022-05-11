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
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.request.product.UpdateStockProductRequest;
import com.example.detai37.ultis.PageableUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

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

    @Transactional
    public List<Bill> getAllBill(){
        List<Bill> list = billRepository.findAll();
        return list;
    }

    @Transactional
    public Bill findBillById(String id){

        Optional<Bill> billOptional = billRepository.findById(id);
        Bill bill  = new Bill();
        if (billOptional != null){
            bill = billOptional.get();
        }
        return bill;
    }

    @Transactional
    public Bill saveBill(CreateBillRequest createBillRequest){
        System.out.println("==========> create bill");
        Bill newBill = new Bill();
//        SaleStaff saleStaff = saleStaffService.findSaleStaffById(createBillRequest.getSaleStaffId());

        // product list
        List<ProductSale> productList = new ArrayList<>();
        for(ProductSaleId productId: createBillRequest.getProductSaleIdList()){
            System.out.println("==========> get product: "+productId.getProductId());
            Product product = productService.findProductById(productId.getProductId());
            ProductSale productSale = ProductSale.builder()
                    .product(product)
                    .quantity(productId.getQuantity())
                    .build();
            productList.add(productSale);
        }

        //save customer
        Customer customer = customerService.findCustomerByPhoneNumber(createBillRequest.getCustomer().getPhoneNumber());
        if (customer == null){
            CreateCustomerRequest createCustomerRequest = CreateCustomerRequest.builder()
                    .firstName(createBillRequest.getCustomer().getFirstName())
                    .lastName(createBillRequest.getCustomer().getLastName())
                    .email(createBillRequest.getCustomer().getEmail())
                    .phoneNumber(createBillRequest.getCustomer().getPhoneNumber())
                    .address(createBillRequest.getCustomer().getAddress())
                    .urlImage("{https://chatappvalo.s3.ap-southeast-1.amazonaws.com/1637245397458_astro.jpg")
                    .build();
            customer = customerService.saveCustomer(createCustomerRequest);
        }

        newBill.setCustomer(customer);
//        newBill.setSaleStaff(saleStaff);
        newBill.setProductList(productList);
        newBill.setDateSale(new Date());
        newBill.setDateDelivery(createBillRequest.getDateDelivery());
        newBill.setPaymentType(createBillRequest.getPaymentType());
        newBill.setStatus(BillStatus.BILL_UNPAID);

        Double price = chargeTotal(newBill);
        newBill.setTotalPrice(price);

        Bill result = billRepository.save(newBill);
        return result;
    }

    @Transactional
    public Bill updateBill(UpdateBillRequest updateBillRequest){
        Bill newBill = billRepository.getById(updateBillRequest.getBillId());
        Customer customer = customerService.findCustomerById(updateBillRequest.getCustomerId());
//        SaleStaff saleStaff = saleStaffService.findSaleStaffById(updateBillRequest.getSaleStaffId());
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
//        newBill.setSaleStaff(saleStaff);
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
