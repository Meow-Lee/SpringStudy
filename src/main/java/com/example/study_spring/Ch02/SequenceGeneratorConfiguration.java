package com.example.study_spring.Ch02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Configuration -> 스프링에 해당 클래스가 구성 클래스임을 알리는 역할
 * @Bean -> 스프링은 @Configuration이 달린 클래스를 보면 해당 클래스에서 @Bean이 붙은 메서드를 찾음
 * Bean의 이름을 따로 명시하지 않으면, 메서드 이름과 동일한 이름으로 빈이 생성됨
 */
@Configuration
public class SequenceGeneratorConfiguration {

    /**
     * Bean을 생성하는 메서드
     */
    @Bean
    public SequenceGenerator sequenceGenerator() {
        SequenceGenerator seqgen = new SequenceGenerator();
//        seqgen.setPrefix("30");
        seqgen.setSuffix("A");
        seqgen.setInitial(100000);
        return seqgen;
    }
}
