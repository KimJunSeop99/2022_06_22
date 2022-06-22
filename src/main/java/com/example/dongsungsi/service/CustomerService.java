package com.example.dongsungsi.service;

import com.example.dongsungsi.model.Customer;
import com.example.dongsungsi.paging.Criteria;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerService
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
public interface CustomerService {

    public Optional<Customer> findById(long id);
    public List<Customer> findByEmailContaining(Criteria criteria);
    public List<Customer> findAll(Criteria criteria);

    //    insert / update 같이 구성되는 메소드
    public Optional<Customer> save(Customer customer);
    public boolean deleteById(Long id);
    public boolean deleteAll();
}











