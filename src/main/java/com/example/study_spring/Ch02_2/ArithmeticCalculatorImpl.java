package com.example.study_spring.Ch02_2;

import org.springframework.stereotype.Component;

/**
 * AspectJ 메서드 시그니처 패턴
 * 애노테이션을 하나 정의한 뒤에, CalculatorPointcuts에서 애노테이션을 스캐닝하도록 정의
 */
@LoggingRequired
@Component("arithmeticCalculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    @Override
    public double add(double a, double b) {
        double result = a + b;
        System.out.println(a + " + " + b + " = " + result);
        return result;
    }

    @Override
    public double sub(double a, double b) {
        double result = a - b;
        System.out.println(a + " - " + b + " = " + result);
        return result;
    }

    @Override
    public double mul(double a, double b) {
        double result = a * b;
        System.out.println(a + " * " + b + " = " + result);
        return result;
    }

    @Override
    public double div(double a, double b) {
        double result = a / b;
        System.out.println(a + " / " + b + " = " + result);
        return result;    }
}
