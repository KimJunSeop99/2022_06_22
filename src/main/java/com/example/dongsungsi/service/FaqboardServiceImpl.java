package com.example.dongsungsi.service;

import com.example.dongsungsi.dao.FaqboardDao;
import com.example.dongsungsi.model.Faqboard;
import com.example.dongsungsi.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.customerspring.service
 * fileName : CustomerServiceImpl
 * author : 4571c
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         4571c          최초 생성
 */
// @Service : 스프링에 객체 생성
@Service
public class FaqboardServiceImpl implements FaqboardService {
    // 스프링에 customerDao 가 이미 생성되어 있음
    // 생성된 객체를 받아오는 어노테이션 : @Autowired
    @Autowired
    private FaqboardDao faqboardDao; // 객체 정의 ( null => 스프링 객체 )

    // 나중에 에러 로그 추적을 위해 하나 만들어둠
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // id 로 회원 조회하는 메소드
    @Override
    public Optional<Faqboard> findByNo(Long no) {
        return faqboardDao.findByNo(no);
    }

    @Override
    public List<Faqboard> findByTitleContaining(Criteria criteria) {
        // 빈 값으로 초기화
        List<Faqboard> faqboards = Collections.emptyList();

        // Email 이 Null 인지 체크
        Optional<String> optionalCriteria
                = Optional.ofNullable(criteria.getTitle());

        // 테이블의 총 데이터 건수
        // Null 이면 "" 로 바꿈
        int totalCount = faqboardDao.selectTotalCount(optionalCriteria.orElse(""));

        // criteria : 페이징 처리 클래스 객체
        criteria.setTotalItems(totalCount);
        // 총 페이지 개수 : 테이블의 총 건수(totalCount) / 페이지당 출력할 데이터 개수(size)
        criteria.setTotalPages(totalCount / criteria.getSize());

        if(criteria.getTitle() == null){
            // title(제목)이 없으면 전채검색을 함
            faqboards = faqboardDao.findAll(criteria);
        } else {
            // title(제목) 이 있으면 제목 검색을 함
            faqboards = faqboardDao.findByTitleContaining(criteria);
        }

        return faqboards;
    }

    // 모든 회원 조회 서비스
    @Override
    public List<Faqboard> findAll(Criteria criteria) {
        return faqboardDao.findAll(criteria);
    }

    // 회원 생성 / 수정 서비스 (insert / update)
    @Override
    public Optional<Faqboard> save(Faqboard faqboard) {
        // db 시퀀스 번호 저장을 위한 변수
        long seqNo = 0;

        // 매개변수 customer 정보 출력
        logger.info("faqboard {}", faqboard);

        // customer.getId() 없으면 insert 문 호출(새로운 회원 생성)
        if (faqboard.getNo() == null) {
            faqboardDao.insertFaqboard(faqboard);
        }
        // 있으면 update 문 호출
        else {
            faqboardDao.updateFaqboard(faqboard);
        }

        // insert 문 후에는 customer 의 id 속성에 값이 저장됨(<selectkey>)
        seqNo = faqboard.getNo();
        logger.info("seqNo {}", seqNo);

        return faqboardDao.findByNo(seqNo);
    }

    @Override
    public boolean deleteByNo(Long no) {
        int queryResult = 0;

        queryResult = faqboardDao.deleteFaqboard(no);

        return (queryResult >= 1) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;

        // delete / update / insert 문은 결과 실행 후
        // 정상 실행되면 건수(삭제/수정/생성)
        queryResult = faqboardDao.deleteAll();

        return (queryResult >= 1) ? true : false;
    }
}