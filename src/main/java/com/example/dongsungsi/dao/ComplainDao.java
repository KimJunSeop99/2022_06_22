package com.example.dongsungsi.dao;

import com.example.dongsungsi.model.Complain;
import com.example.dongsungsi.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.dao
 * fileName : CustomerDao
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
// mybatis 사용시 아래 어노테이션을 붙임
//    인터페이스 구현 => .xml에서 함(스프링에 자동 객체 생성)
@Mapper
public interface ComplainDao {
    // 제목 검색을 위한 메소드

    // TODO: findAll() -> findAll(Criteria criteria)
    // 모든 데이터 목록 조회
    public List<Complain> findAll(Criteria criteria);

    public List<Complain> findByTitleContaining(Criteria criteria);

    // 제목에 따른 데이터 건수를 세는 메소드
    public int selectTotalCount(String title);

    // id 에 해당하는 값을 조회
    // Optional : null 을 방지하는 클래스
    public Optional<Complain> findById(Long id);

    // Tutorial 테이블에 데이터 넣기 메소드
    public int insertComplain(Complain complain);

    // Tutorial 테이블에 데이터 수정 메소드
    public int updateComplain(Complain complain);

    // Tutorial 테이블에 데이터 삭제 메소드 ( deleteYn = 'Y' )
    public int deleteComplain(Long id);

    // Tutorial 테이블에 데이터 모두 삭제 메소드 ( deleteYn = 'Y' )
    public int deleteAll();
}












