package com.example.dongsungsi.service;

import com.example.dongsungsi.model.Complain;
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
public interface ComplainService {

    public Optional<Complain> findById(long id);

    public List<Complain> findByTitleContaining(Criteria criteria);

    public List<Complain> findAll(Criteria criteria);

    //    insert / update 같이 구성되는 메소드
    public Optional<Complain> save(Complain complain);

    public boolean deleteById(Long id);

    public boolean deleteAll();
}











