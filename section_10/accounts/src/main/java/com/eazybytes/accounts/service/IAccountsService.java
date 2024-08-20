package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDto;

public interface IAccountsService {
    
    /**
     * 
     * @param CustomerDto
     */
    void createAccount(CustomerDto customerDto); 

    /**
     * 
     * @param mobileNumber
     * @return CustomerDto
     */
    CustomerDto fetchAccount(String mobileNumber);

    
    /**
     * 
     * @param CustomerDto
     * @return boolean
     */
    boolean updateAccount(CustomerDto customerDto);


    /**
     * 
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);

}
