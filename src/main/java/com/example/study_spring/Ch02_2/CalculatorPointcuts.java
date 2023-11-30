package com.example.study_spring.Ch02_2;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 여러 애스펙트가 포인트컷을 공유하는 경우라면 공통 클래스 한 곳에 포인트컷을 모아두는 편이 좋음
 *
 * AspectJ 메서드 시그니처 패턴, 시그니처를 기준으로 여러 메서드를 매치하는 것
 * @Pointcut("annotation(com.example.study_spring.Ch02_2.LoggingRequired)")
 *
 * 타입 시그니처 패턴으로, 특정한 타입 내부의 모든 조인포인트를 매치하는 포인트컷 표현식
 * @Pointcut("within(com.example.study_spring.Ch02_2.LoggingRequired)")
 *
 * 포인트컷 표현식 조합
 * &&, ||, ! 등의 연산자로 조합할 수 있음
 */
@Aspect
public class CalculatorPointcuts {
    //    @Pointcut("execution(* *.*(..))")
//    @Pointcut("annotation(com.example.study_spring.Ch02_2.LoggingRequired)")
//    @Pointcut("within(com.example.study_spring.Ch02_2.LoggingRequired)")
    @Pointcut("within(ArithmeticCalculator+)")
    public void arithmeticOperation() {}

    @Pointcut("within(UnitCalculator+)")
    public void unitOperation() {}

    @Pointcut("arithmeticOperation() || unitOperation()")
    public void loggingOperation() {}
}
