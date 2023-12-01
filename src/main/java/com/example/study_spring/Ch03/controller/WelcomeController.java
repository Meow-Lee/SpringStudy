package com.example.study_spring.Ch03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.Date;

/**
 * WelcomeController 클래스는 Date객체를 생성해 오늘 날짜를 설정하고 입력받은 Model 객체에 추가해서 뷰에서 화면에 표시하게 함
 */
@Controller
//@RequestMapping("/welcome")
public class WelcomeController {

    /**
     * @GetMapping 등으로 더 간결하게 표현할 수 있음
     */
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/welcome")
    public String welcome(Model model) {
        Date today = new Date();
        model.addAttribute("today", today);
        return "welcome";
    }
}
