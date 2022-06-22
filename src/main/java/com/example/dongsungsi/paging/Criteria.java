package com.example.dongsungsi.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
    // 현재 페이지 번호
    // Long(객체) long(기본형)
    // Integer(객체) int(기본형)
    private Integer page; // 현재 페이지 번호
    private Integer size; // 1페이지 당 출력할 데이터 개수

    private Integer totalItems; // 테이블 총 데이터 개수

    // 계산 공식 : 테이블 총 데이터 개수 / 1페이지 당 출력할 데이터 개수
    private Integer totalPages; // 총 페이지 수 (totalItems / size)

    private String title; // 검색 제목

    private String email;

    private String content;

    private String targetPage;

    private String writer;

    private Long no;

    // 기본 페이지 정보 저장
    public Criteria() {
        this.page = 0; // 현재 페이지 번호
        this.size = 3; // 페이지 당 데이터 건수
    }

}