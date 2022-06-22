package com.example.dongsungsi.service;

import com.example.dongsungsi.model.Faqboard;
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
public interface FaqboardService {

    public Optional<Faqboard> findByNo(Long no);

    public List<Faqboard> findByTitleContaining(Criteria criteria);

    public List<Faqboard> findAll(Criteria criteria);

    //    insert / update 같이 구성되는 메소드
    public Optional<Faqboard> save(Faqboard faqboard);

    public boolean deleteByNo(Long no);

    public boolean deleteAll();
}











