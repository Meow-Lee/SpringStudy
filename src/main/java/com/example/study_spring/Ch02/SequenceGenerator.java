package com.example.study_spring.Ch02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceGenerator {
    /**
     * @Qualifier에 이름을 주어 후보 빈을 명시할 수 있음
     */
    @Autowired
    @Qualifier("datePrefixGenerator")
    private PrefixGenerator prefixGenerator;
    private String prefix;
    private String suffix;
    private int initial;
    private final AtomicInteger counter = new AtomicInteger();

    public SequenceGenerator() {
    }

    public SequenceGenerator(PrefixGenerator prefixGenerator, String suffix, int initial) {
        this.prefixGenerator = prefixGenerator;
        this.suffix = suffix;
        this.initial = initial;
    }

    /**
     * @Autowired -> POJO 세터 메서드에도 직접 적용 가능
     * 아래와 같이 하게 되면, prefixGenerator와 타입 호환되는 빈이 연결됨
     * 스프링은 기본적으로 @Autowired를 붙인 필수 프로퍼티에 해당하는 빈을 찾지 못하면 예외를 던짐
     * 따라서, @Autowired의 속성을 required=false로 지정해주면, 빈을 찾지 못하더라도 그냥 지나치게 됨
     */
    @Autowired(required = false)
    public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    /**
     * 메서드 인수를 연결하는 쓰임새도 있음
     */
    @Autowired
    public void myOwnCustomInjectionName(@Qualifier("datePrefixGenerator") PrefixGenerator prefixGenerator) {
        this.prefixGenerator = prefixGenerator;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setInitial(int initial) {
        this.initial = initial;
    }

    public String getSequence() {
        StringBuilder builder = new StringBuilder();
        builder.append(prefix)
                .append(initial)
                .append(counter.getAndIncrement())
                .append(suffix);
        return builder.toString();
    }
}
