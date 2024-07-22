package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDTO;

public interface IAccountsService {
    
    /**
     * 
     * @param customerDTO
     */
    void createAccount(CustomerDTO customerDTO); 

    /**
     * 
     * @param mobileNumber
     * @return CustomerDTO
     */
    CustomerDTO fetchAccount(String mobileNumber);

    
    /**
     * 
     * @param customerDTO
     * @return boolean
     */
    boolean updateAccount(CustomerDTO customerDTO);


    /**
     * 
     * @param mobileNumber
     * @return
     */
    boolean deleteAccount(String mobileNumber);

}
