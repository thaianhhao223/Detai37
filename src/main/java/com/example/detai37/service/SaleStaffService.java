package com.example.detai37.service;

import com.example.detai37.common.SaleStaffStatus;
import com.example.detai37.entity.Customer;
import com.example.detai37.entity.SaleStaff;
import com.example.detai37.repository.SaleStaffRepository;
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.request.customer.UpdateCustomerRequest;
import com.example.detai37.request.customer.UpdateStatusCustomerRequest;
import com.example.detai37.request.salestaff.CreateSaleStaffRequest;
import com.example.detai37.request.salestaff.UpdateSaleStaffRequest;
import com.example.detai37.request.salestaff.UpdateStatusSaleStaffRequest;
import com.example.detai37.ultis.MappingUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleStaffService {
    private SaleStaffRepository saleStaffRepository;


    public SaleStaffService(SaleStaffRepository saleStaffRepository) {
        this.saleStaffRepository = saleStaffRepository;
    }

    public List<SaleStaff> getAllSaleStaff(){
        List<SaleStaff> saleStaffList = saleStaffRepository.findAll();
        return saleStaffList;
    }

    public SaleStaff findSaleStaffById(String id){
        Optional<SaleStaff> saleStaffOptional = saleStaffRepository.findById(id);
        SaleStaff saleStaff = null;
        if (saleStaffOptional != null){
            saleStaff = saleStaffOptional.get();
        }
        return saleStaff;
    }

    public SaleStaff saveSaleStaff(CreateSaleStaffRequest createSaleStaffRequest){
        SaleStaff saleStaff = MappingUtils.mapObject(createSaleStaffRequest, SaleStaff.class);
        saleStaff.setStatus(SaleStaffStatus.SALE_STAFF_ACTIVE);
        SaleStaff result = saleStaffRepository.save(saleStaff);
        return result;
//        return customer;
    }

    public SaleStaff updateSaleStaff(UpdateSaleStaffRequest updateSaleStaffRequest){
        SaleStaff saleStaff = MappingUtils.mapObject(updateSaleStaffRequest, SaleStaff.class);
        SaleStaff odlSaleStaff = saleStaffRepository.getById(updateSaleStaffRequest.getId());
        if(odlSaleStaff!=null){
            saleStaff.setStatus(odlSaleStaff.getStatus());
        }
//        else {
//            throw new Exception();
//        }
        SaleStaff result = saleStaffRepository.save(saleStaff);
        return result;
    }

    public SaleStaff updateStatusSaleStaff(UpdateStatusSaleStaffRequest updateStatusSaleStaffRequest){
        SaleStaff oldSaleStaff = saleStaffRepository.getById(updateStatusSaleStaffRequest.getId());
        if(oldSaleStaff!=null){
            oldSaleStaff.setStatus(updateStatusSaleStaffRequest.getStatus());
        }
//        Customer customer = MappingUtils.mapObject(updateCustomerRequest, Customer.class);
        SaleStaff result = saleStaffRepository.save(oldSaleStaff);
        return result;
    }
}
