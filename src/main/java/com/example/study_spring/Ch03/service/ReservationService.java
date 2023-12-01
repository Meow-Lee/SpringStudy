package com.example.study_spring.Ch03.service;

import com.example.study_spring.Ch03.domain.Reservation;

import java.util.List;

public interface ReservationService {
    public List<Reservation> query(String courtName);
}
