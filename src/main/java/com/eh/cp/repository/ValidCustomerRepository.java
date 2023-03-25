package com.eh.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eh.cp.model.ValidCustomer;
/**
 * @author MD. EMRAN HOSSAIN
 * @since 24 MAR, 2023
 * @version 1.1
 */
@Repository
public interface ValidCustomerRepository extends JpaRepository<ValidCustomer, Long> {

}
