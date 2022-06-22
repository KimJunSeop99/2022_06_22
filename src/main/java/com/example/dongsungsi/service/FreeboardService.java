package com.example.dongsungsi.service;

import com.example.dongsungsi.model.Freeboard;
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
public interface FreeboardService {

    public Optional<Freeboard> findByNo(Long no);

    public List<Freeboard> findByTitleContaining(Criteria criteria);

    public List<Freeboard> findAll(Criteria criteria);

    //    insert / update 같이 구성되는 메소드
    public Optional<Freeboard> save(Freeboard freeboard);

    public boolean deleteByNo(Long no);

    public boolean deleteAll();
}











