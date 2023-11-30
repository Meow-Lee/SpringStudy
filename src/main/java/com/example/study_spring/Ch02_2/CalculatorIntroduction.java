package com.example.study_spring.Ch02_2;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * 인트로덕션 대상 클래스는 @DeclareParents의 value 속성으로 지정하며 이 애노테이션을 붙인 필드형에 따라 들여올 인터페이스 결정
 * 이 새 인터페이스에서 사용ㅇ할 구현 클래스는 defaultImpl 속성에 명시
 * <p>
 * 이렇게 인트로덕션을 사용하여 ArithmeticCalculatorImpl 클래스로 두 인터페이스를 동적으로 들여올 수 있음
 */

@Aspect
@Component
public class CalculatorIntroduction {
    @DeclareParents(
            value = "com.example.study_spring.Ch02_2.ArithmeticCalculatorImpl",
            defaultImpl = MaxCalculatorImpl.class
    )
    public MaxCalculator maxCalculator;

    @DeclareParents(
            value = "com.example.study_spring.Ch02_2.ArithmeticCalculatorImpl",
            defaultImpl = MinCalculator.class
    )
    public MinCalculator minCalculator;

    /**
     * 상태 필드가 위치한 구현 클래스의 인터페이스를 기존 객체에 들여온 다음, 특정 조건에 따라 상태값을 바꾸는 어드바이스를 작성
     */
    @DeclareParents(
            value = "com.example.study_spring.Ch02_2.*CalculatorImpl",
            defaultImpl = CounterImpl.class
    )
    public Counter counter;

    /**
     * 계산기 메서드를 한번씩 호출할 때마다 counter 값을 하나씩 증가 시키는 것
     */
    @After("execution(* com.example.study_spring.Ch02_2.*Calculator.*(..))"
            + " && this(counter)")
    public void increaseCount(Counter counter) {
        counter.increase();
    }
}
