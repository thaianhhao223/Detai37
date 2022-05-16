package com.example.detai37.service;

import com.example.detai37.common.CustomerStatus;
import com.example.detai37.common.UserType;
import com.example.detai37.entity.Account;
import com.example.detai37.entity.Customer;
import com.example.detai37.repository.AccountRepository;
import com.example.detai37.request.RegisterRequest;
import com.example.detai37.request.account.CreateAccountRequest;
import com.example.detai37.request.account.LoginAccountRequest;
import com.example.detai37.request.account.UpdateAccountRequest;
import com.example.detai37.request.customer.CreateCustomerRequest;
import com.example.detai37.response.LoginInfoResponse;
import com.example.detai37.ultis.MappingUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    private final String DEFAULT_IMG_URL = "https://chatappvalo.s3.ap-southeast-1.amazonaws.com/1637245397458_astro.jpg";

    public AccountService(AccountRepository accountRepository,
                          CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }

    public Account findUserByUserName(String userName){
        Account account = accountRepository.getById(userName);
        return account;
    }

    /**
     * Login to server
     * @param loginAccountRequest
     * @return loginInfoResponse
     */
    public LoginInfoResponse loginAccount(LoginAccountRequest loginAccountRequest){
        Optional<Account> optional = accountRepository.findById(loginAccountRequest.getUserName());
        if (optional.isPresent()){
            Account account = optional.get();
            if(loginAccountRequest.getPassWord().equals(account.getPassWord())){
                return MappingUtils.mapObject(account, LoginInfoResponse.class);
            }
        }
        return new LoginInfoResponse();
    }

    /**
     * Registry an account
     * @param registerRequest
     * @return saved account
     * @author Lại Văn Vượng
     */
    @Transactional
    public Account registration(RegisterRequest registerRequest){
        CreateCustomerRequest customerRequest = CreateCustomerRequest.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .address(registerRequest.getAddress())
                .birthDate(registerRequest.getBirthDate())
                .urlImage(DEFAULT_IMG_URL)
                .status(CustomerStatus.NEW_CUSTOMER)
                .build();
        Customer customer = customerService.saveCustomer(customerRequest);

        Account account = Account.builder()
                .userName(registerRequest.getUserName())
                .passWord(registerRequest.getPassWord())
                .userId(customer.getId())
                .userType(UserType.CUSTOMER)
                .build();
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }

    public Account saveAccount(CreateAccountRequest createAccount){
        Account account = MappingUtils.mapObject(createAccount,Account.class);
        Account result = accountRepository.save(account);
        return result;
    }

    public Account updateAccount(UpdateAccountRequest updateAccountRequest){
        Account account = MappingUtils.mapObject(updateAccountRequest,Account.class);
        Account result = accountRepository.getById(account.getUserName());
        result.setPassWord(account.getPassWord());
        result.setUserType(account.getUserType());
        result.setUserId(account.getUserId());
        accountRepository.save(result);
        return result;
    }
}
