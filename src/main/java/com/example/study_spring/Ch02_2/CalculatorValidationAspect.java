package com.example.study_spring.Ch02_2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 같은 조인포인트에 애스펙트를 여러 개 적용할 경우, 애스펙트 간 우선순위를 정함
 * 애스펙트 간 우선순위는 Ordered 인터페이스를 구현하거나 @Order 애노테이션을 붙여 지정
 * Order 인터페이스를 구현할 경우, 반환하는 값이 작을수록 우선순위가 높음
 * 따라서, @Order(0)를 쓴 CalculatorValidationAspect 클래스가 @Order(1)인 CalculatorLoggingAspect보다 우선순위가 높음
 */

@Aspect
@Component
@Order(0)
public class CalculatorValidationAspect {
    @Before("execution(* *.*(double, double))")
    public void validateBefore(JoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            validate((Double) arg);
        }
    }

    private void validate(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Positive numbers only");
        }
    }
}
