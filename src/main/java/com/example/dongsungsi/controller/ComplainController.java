package com.example.dongsungsi.controller;

import com.example.dongsungsi.model.Complain;
import com.example.dongsungsi.paging.Criteria;
import com.example.dongsungsi.service.ComplainServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName : com.example.customerspring.controller
 * fileName : CustomerController
 * author : 4571c
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         4571c          최초 생성
 */
@CrossOrigin(origins = "http://localhost:8080")
// @RestController : 통신을 json 형태로 주고받고 싶을 때 사용
@RestController
// @RequestMapping("/api") : http://localhost:8000/api
@RequestMapping("/api")
public class ComplainController {
    // logger 찍기를 위한 객체 만듬
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // @Autowired : 스프링 객체 하나 받아오기
    @Autowired
    private ComplainServiceImpl complainService; // 객체 정의( null => 스프링 객체)

    // 모든 회원 조회 메뉴
    @GetMapping("/complains")
    public ResponseEntity<Map<String,Object>> getAllComplains(Criteria criteria) {
        logger.info("criteria {}", criteria);
        // 모든 회원 조회 서비스 호출
        List<Complain> complains = complainService.findByTitleContaining(criteria);

        try {
            if (complains.isEmpty()) {
                // Vue 성공메세지 + 객체를 전송
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            logger.info("criteria {}", criteria);
            Map<String, Object> response = new HashMap<>();
            response.put("complains", complains);
            // page : 현재 페이지
            response.put("currentPage", criteria.getPage());
            // totalItems : 총 데이터 건수
            response.put("totalItems", criteria.getTotalItems());
            // totalPages : 총 페이지 개수
            response.put("totalPages", criteria.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 에러메세지 전송
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 회원 생성 메뉴
    @PostMapping("/complains")
    public ResponseEntity<Object>
    createComplain(@RequestBody Complain complain) {
        // save 리턴값 Optional<Customer> => save(customer).get() 객체를 꺼냄
        logger.info("complain {} ",complain);
        Complain savedComplain = complainService.save(complain).get();
        try {
            return new ResponseEntity<Object>(savedComplain, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 보낼 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // id 로 회원 조회 메뉴
    @GetMapping("/complains/{id}")
    public ResponseEntity<Object> getComplainById(@PathVariable("id") Long id) {
        // id 로 회원 조회하는 서비스를 호출 ( Optional 떼내기 : get() )
        Complain complain = complainService.findById(id).get();

        try {
            if (complain != null) {
                // 성공 시 Vue 에 객체 + 성공메세지 전송
                return new ResponseEntity<Object>(complain, HttpStatus.OK);
            } else {
                // Vue 에 데이터가 없을 경우 Not found 전송
                return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // id 회원 수정 메뉴
    // Vue 에서 전송 : url 매개변수 + 객체
    // @PathVariable : url 매개변수를 받는 어노테이션
    // @RequestBody : 객체를 받는 어노테이션
    @PutMapping("/complains/{id}")
    public ResponseEntity<Object> updateComplains(
            @PathVariable("id") Long id,
            @RequestBody Complain complain
    ) {
        try {
            // customer 에 id 값 저장
            complain.setId(id);
            // save : id 가 null 일 경우 insert , id 가 null이 아닐경우 update
            // Optional<Customer> => Customer 객체를 꺼낼려면 get() 메소드를 호출해야함
            Complain savedComplain = complainService.save(complain).get();
            // Vue 에 DB에 저장된 객체 + 상태메세지를 전송(OK)
            return new ResponseEntity<Object>(savedComplain, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // Vue 에 에러 메세지 전송
            return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
        }
    }

    // id 로 회원 삭제 메뉴
    @PutMapping("/complains/deletion/{id}")
    public ResponseEntity<HttpStatus> deleteComplain(
            @PathVariable("id") Long id
    ) {
        try {
            // id 로 삭제 서비스를 호출(내부적으로 update 문이 실행됨)
            complainService.deleteById(id);
            // Vue 에 전송메세지 전송
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            // 웹 애플리케이션 개발 : 클라이언트(Vue,React,HTML) <-> 서버(SpringBoot, Node)
            // Vue(클라이언트) 에 에러메세지 전송
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

        }
    }
}