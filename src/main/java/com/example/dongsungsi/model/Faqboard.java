package com.example.dongsungsi.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName : com.example.customerspring.model
 * fileName : Customer
 * author : ds
 * date : 2022-06-07
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-07         ds          최초 생성
 */
@Getter
@Setter
@ToString
public class Faqboard {
//    Long(객체), long(일반자료형) => 8byte 정수형
    private Long no; // 게시글 번호
    private String title; // 컴플레인 제목
    private String content; // 컴플레인 내용
    private String writer;  // 작성자
    private String deleteYn; // 삭제 여부
    private String insertTime; // 생성날짜
    private String updateTime; // 수정날짜
    private String deleteTime; // 삭제날짜
}











