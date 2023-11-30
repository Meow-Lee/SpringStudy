package com.example.study_spring.Ch02_2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * 포인트컷으로 매치한 실행 지점을 조인포인트 라고 함
 * 포인트컷은 여러 조인포인트를 매치하기 위해 지정한 표현식
 * 이렇게 매치된 조인포인트에서 해야할 일이 바로 어드바이스
 *
 */

public class Main {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(CalculatorConfiguration.class);

//        ArithmeticCalculator arithmeticCalculator = ac.getBean("arithmeticCalculator", ArithmeticCalculator.class);
//        arithmeticCalculator.add(1, 2);
//        arithmeticCalculator.sub(4, 3);
//        arithmeticCalculator.mul(2, 3);
//        arithmeticCalculator.div(4, 2);
//
//        UnitCalculator unitCalculator = ac.getBean("unitCalculator", UnitCalculator.class);
//        unitCalculator.kilogramToPound(10);
//        unitCalculator.kilometerToMile(5);

        /**
         * The method add() begins with [1.0, 2.0]
         * The method add() begins with [1.0, 2.0]
         * The method add() begins
         * 1.0 + 2.0 = 3.0
         * The method add() ends with 3.0
         * The method add() ends
         * The method add() ends with
         * The method sub() begins with [4.0, 3.0]
         * The method sub() begins with [4.0, 3.0]
         * 4.0 - 3.0 = 1.0
         * The method sub() ends with 1.0
         * The method sub() ends
         * The method sub() ends with
         * The method mul() begins with [2.0, 3.0]
         * The method mul() begins with [2.0, 3.0]
         * 2.0 * 3.0 = 6.0
         * The method mul() ends with 6.0
         * The method mul() ends
         * The method mul() ends with
         * The method div() begins with [4.0, 2.0]
         * The method div() begins with [4.0, 2.0]
         * 4.0 / 2.0 = 2.0
         * The method div() ends with 2.0
         * The method div() ends
         * The method div() ends with
         * The method kilogramToPound() begins with [10.0]
         * The method kilogramToPound() begins with [10.0]
         * 10.0 kilogram = 22.0 pound
         * The method kilogramToPound() ends with 22.0
         * The method kilogramToPound() ends
         * The method kilogramToPound() ends with
         * The method kilometerToMile() begins with [5.0]
         * The method kilometerToMile() begins with [5.0]
         * 5.0 kilometer = 3.1 mile
         * The method kilometerToMile() ends with 3.1
         * The method kilometerToMile() ends
         * The method kilometerToMile() ends with
         */

        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) ac.getBean("arithmeticCalculator");

//        MaxCalculator maxCalculator = (MaxCalculator) arithmeticCalculator;
//        maxCalculator.max(1, 2);
//
//        MinCalculator minCalculator = (MinCalculator) arithmeticCalculator;
//        minCalculator.min(1, 2);

        UnitCalculator unitCalculator = (UnitCalculator) ac.getBean("unitCalculator");

        Counter arithmeticCounter = (Counter) arithmeticCalculator;
        System.out.println(arithmeticCounter.getCount());

        Counter unitCounter = (Counter) unitCalculator;
        System.out.println(unitCounter.getCount());
    }
}
