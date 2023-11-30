package com.example.study_spring.Ch02_2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class CalculatorLoggingAspect {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 포인트컷 표현식을 여러 번 되풀이해서 쓸 경우엔 어드바이스 애노테이션에 직접 써넣는 것보다 재사용할 방법이 필요
     * @Pointcut을 이용하면 포인트컷만 따로 정의해 재사용 가능
     * 또한, 단순 메서드로 선언할 수 있음
     */
    @Pointcut("execution(* *.*(..))")
    private void loggingOperation(){}

    /**
     * 포인트컷 표현식은 ArithmeticCalculator 인터페이스의 add() 메서드 실행을 가리킴
     * 앞부분의 와일드카드(*)는 모든 수정자, 모든 반환형을 매치함을 의미
     * 인수 목록 부분에 쓴 두 점(..)은 인수 개수는 몇 개라도 좋다는 뜻
     */
    @Before("execution(* ArithmeticCalculator.add(..))")
    public void logBefore() {
        log.info("The method add() begins");
    }

    /**
     * 어드바이스가 현재 조인포인트의 세부적인 내용에 액세스하려면, JoinPoint형 인수를 어드바이스 메서드에 선언해야함
     * 그러면 메서드명, 인수값 등 자세한 조인포인트 정보를 조회할 수 있음
     */
//    @Before("execution(* *.*(..))")
    @Before("CalculatorPointcuts.loggingOperation()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("The method " + joinPoint.getSignature().getName()
                + "() begins with " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * AOP(Aspect-Oriented Programming)에서 어드바이스는 여러 조인포인트, 즉 프로그램 실행 지점 곳곳에 적용됨
     * 어드바이스가 정확하게 작동하려면 조인포인트에 관한 세부 정보가 필요한 경우가 많음
     * 따라서, JoinPoint형 인수를 사용하여 정보를 얻을 수 있음
     * 조인포인트 유형(스프링 AOP의 메서드 실행만 해당)
     * 메서드 시그니처(선언 타입 및 메서드명)
     * 인수값
     * 대상 객체와 프록시 객체
     *
     * 프록시로 감싼 원본 빈은 대상 객체(target object)라고 하며 프록시 객체는 this로 참조
     * Target class : org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint
     * This class : jdk.proxy2.$Proxy22
     */
    @Before("execution(* *.*(..))")
    public void logJoinPoint(JoinPoint joinPoint) {
        log.info("Join point kind : {}", joinPoint.getKind());
        log.info("Signature declaring type : {}", joinPoint.getSignature().getDeclaringType());
        log.info("Signature name : {}", joinPoint.getSignature().getName());
        log.info("Arguments : {}", Arrays.toString(joinPoint.getArgs()));
        log.info("Target class : {}", joinPoint.getClass().getName());
        log.info("This class : {}", joinPoint.getThis().getClass().getName());
    }

    /**
     * @After 어드바이스는 조인포인트가 끝아면 실행되는 메서드로, 조인포인트가 정상 실행되든, 도중에 예외가 발생하든 상관없이 실행
     */
    @After("execution(* *.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("The method " + joinPoint.getSignature().getName()
                + "() ends");
    }

    /**
     * @AfterReturning logAfter 메서드에서 조인포인트가 값을 반환할 경우에만 로깅하고 싶을때 사용
     */
//    @AfterReturning("execution(* *.*(..))")
//    public void logAfterReturning(JoinPoint joinPoint) {
//        log.info("The method {}() ends with {}", joinPoint.getSignature().getName(), result);
//    }
    @AfterReturning(
//            pointcut = "execution(* *.*(..))",
//            pointcut = "loggingOperation()",
            pointcut = "CalculatorPointcuts.loggingOperation()",
            returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("The method " + joinPoint.getSignature().getName() + "() ends with " + result);
    }

    /**
     * @AfterThrowing 어드바이스는 조인포인트 실행 도중 예외가 날 경우에만 실행됨
     * <p>
     * 조인포인트에서 발생한 에러/예외를 모두 가져올 때
     * <p>
     * 특정한 예외만 관심있다면 그 타입을 인수에 선언함, 주어진 타입과 호환되는 예외가 났을 경우에만 어드바이스 실행
     */
//    @AfterThrowing("execution(* *.*(..))")
//    public void logAfterThrowing(JoinPoint joinPoint) {
//        log.error("An exception has been thrown in {}()", joinPoint.getSignature().getName());
//    }

//    @AfterThrowing(
//            pointcut = "execution(* *.*(..))",
//            throwing = "e")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//        log.error("An exception {} has been thrown in {}()", e, joinPoint.getSignature().getName());
//    }
    @AfterThrowing(
//            pointcut = "execution(* *.*(..))",
            pointcut = "loggingOperation()",
            throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, IllegalArgumentException e) {
        log.error("Illegal argument {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
    }

    /**
     * @Around -> 가장 강력한 어드바이스로, 조인포인트를 완전히 장악하기 때문에 앞서 나온 어드바이스 모두 Around 어드바이스로 조합할 수 있음
     * 심지어 원본 조인포인트를 언제 실행할지, 실행 자체를 할지 말지, 계속 실행할지 여부까지도 제어할 수 있음
     * Around 어드바이스의 조인포인트 인수형은 ProceedingJoinPoint로 고정되어 있음
     */
//    @Around("execution(* *.*(..))")
//    @Around("loggingOperation()")
    @Around("CalculatorPointcuts.loggingOperation()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("The method {}() begins with {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));

        try {
            Object result = joinPoint.proceed();
            log.info("The method {}() ends with ", joinPoint.getSignature().getName(), result);
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument {} in {}()", Arrays.toString(joinPoint.getArgs()), joinPoint.getSignature().getName());
            throw e;
        }
    }
}
