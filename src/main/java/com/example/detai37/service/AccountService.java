package com.example.detai37.service;

import com.example.detai37.entity.Account;
import com.example.detai37.repository.AccountRepository;
import com.example.detai37.request.account.CreateAccountRequest;
import com.example.detai37.request.account.LoginAccountRequest;
import com.example.detai37.request.account.UpdateAccountRequest;
import com.example.detai37.ultis.MappingUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public Account findUserByUserName(String userName){
        Account account = accountRepository.getById(userName);
        return account;
    }

    public boolean loginAccount(LoginAccountRequest loginAccountRequest){
        Account account = accountRepository.getById(loginAccountRequest.getUserName());
        if(loginAccountRequest.getPassWord().equals(account.getPassWord())){
            return true;
        }
        return false;
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
