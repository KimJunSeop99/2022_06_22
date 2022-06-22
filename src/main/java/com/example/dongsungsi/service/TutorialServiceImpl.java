package com.example.dongsungsi.service;

import com.example.dongsungsi.dao.TutorialDao;
import com.example.dongsungsi.model.Tutorial;
import com.example.dongsungsi.paging.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * packageName : com.example.dongsungsi.service
 * fileName : TutorialServiceImpl
 * author : 4571c
 * date : 2022-05-25
 * description : Dao, Model 이용해서 업무로직 구현 클래스
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-05-25         4571c          최초 생성
 */
@Service
public class TutorialServiceImpl implements TutorialService {
    // @Autowired : 스프링에 생성된 객체를 받아옴
    @Autowired
    private TutorialDao tutorialDao; // 객체 정의 {null} => 스프링 객체

    // 향후에 로그 찍고 싶을때 사용하는 객체
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Optional<Tutorial> findById(long id) {
        return tutorialDao.findById(id);
    }

    @Override
    public List<Tutorial> findByPublished(String published) {
        return tutorialDao.findByPublished(published);
    }

    @Override
    public List<Tutorial> findByTitleContaining(Criteria criteria) {
        // 빈 값으로 초기화
        List<Tutorial> tutorials = Collections.emptyList();

        // Title이 Null 체크
        Optional<String> optionalCriteria = Optional.ofNullable(criteria.getTitle());

        // 테이블의 총 데이터 건수
        int totalCount = tutorialDao.selectTotalCount(optionalCriteria.orElse(""));

        // criteria : 페이징 처리 클래스 객체
        criteria.setTotalItems(totalCount);

        // 총 페이지 개수 : 테이블의 총 건수(totalCount) / 페이지 당 출력할 데이터 개수(size)
        criteria.setTotalPages(totalCount / criteria.getSize());

        if(criteria.getTitle() == null){
            // title(제목)이 없으면(==null) 전채 검색을 함
            tutorials = tutorialDao.findAll(criteria);
        } else {
            // title(제목) 이 있으면(!=null) 제목 검색을 함
            tutorials = tutorialDao.findByTitleContaining(criteria);
        }

        return tutorials;
    }

    @Override
    public List<Tutorial> findAll(Criteria criteria) {
        return tutorialDao.findAll(criteria);
    }

    @Override
    public boolean save(Tutorial tutorial) {
        int queryResult = 0;

        logger.info("tutorial {} : " , tutorial);

        if(tutorial.getId() == null)
        {
            // id 값이 없으면 insert 문 실행
            queryResult = tutorialDao.insertTutorial(tutorial);
        }else {
            // id 값이 있으면 update 문 실행
            queryResult = tutorialDao.updateTutorial(tutorial);
        }
        return (queryResult >= 1) ? true : false;
    }

    @Override
    public boolean deleteById(Long id) {
        int queryResult = 0;

        queryResult = tutorialDao.deleteTutorial(id);
        return (queryResult >= 1) ? true : false;
    }

    @Override
    public boolean deleteAll() {
        int queryResult = 0;

        queryResult = tutorialDao.deleteAll();
        return (queryResult >= 1) ? true : false;
    }
}