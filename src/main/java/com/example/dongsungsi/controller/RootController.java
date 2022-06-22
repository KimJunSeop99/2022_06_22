package com.example.dongsungsi.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;

/**6
 * packageName : com.example.dongsungsi.controller
 * fileName : RootController
 * author : Seok
 * date : 2022-06-14
 * description :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-14         Seok          최초 생성
 */

public class RootController implements ErrorController {

    // 에러가 (Not Found) 발생하면 강제적으로 index.html 주소로 변경(redirect)
    @GetMapping("/error")
    public String redirectRoot() {
        return "index.html";
    }
}