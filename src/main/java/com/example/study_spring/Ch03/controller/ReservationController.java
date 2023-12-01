package com.example.study_spring.Ch03.controller;

import com.example.study_spring.Ch03.domain.Reservation;
import com.example.study_spring.Ch03.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/reservationQuery")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * setupForm을 기본 HTTP GET 핸들러 메서드로 호출 -> JSP 같은 구현체 템플릿에서 하드코딩된 데이터를 뷰에서 보여주겠다
     * 혹은 기본 뷰 이름이 요청 URL에 따라 결정된다는 뜻 (만약, /reservationQuery로 요청이 왔으면 reservationQuery라는 이름의 뷰가 반환
     */

    @GetMapping
    public void setupForm() {}

    /**
     * @RequestParam 으로 요청 매개변수를 추출해 쓰겠다는 선언
     * 요청이 /reservationQuery?courtName=<코트명> URL로 POST 요청을 하면 <코트명>을 courtName이라는 변수로 받는 것
     */
    @PostMapping
    public String submitForm(@RequestParam("courtName") String courtName, Model model) {
        List<Reservation> reservations = Collections.emptyList();
        if (courtName != null) {
            reservations = reservationService.query(courtName);
        }
        model.addAttribute("reservations", reservations);
        return "reservationQuery";
    }
}
