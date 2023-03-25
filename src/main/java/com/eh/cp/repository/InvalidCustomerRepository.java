package com.eh.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eh.cp.model.InvalidCustomer;
/**
 * @author MD. EMRAN HOSSAIN
 * @since 24 MAR, 2023
 * @version 1.1
 */
@Repository
public interface InvalidCustomerRepository extends JpaRepository<InvalidCustomer, Long> {

}
