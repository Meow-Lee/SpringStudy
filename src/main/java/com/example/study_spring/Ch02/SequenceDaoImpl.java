package com.example.study_spring.Ch02;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Component("sequenceDao") -> 스프링은 이 클래스를 이용해 POJO를 생성
 * sequenceDao를 넣으면 이를 빈 인스턴스 ID로 설정하고, 없으면 소문자로 시작하는 비규격 클래스명을 빈 이름으로 기본 할당(sequenceDaoImpl)
 * @Component는 스프링이 발견할 수 있게 POJO에 붙이는 범용 애노테이션
 * 스프링에는 persistence(영속화), service(서비스), presentation(표현) 등 세가지 레이어 존재 / @Repository, @Service, @Controller
 * POJO의 쓰임새가 명확하지 않을 땐 그냥 @Component를 붙여도 되지만, 특정 용도에 맞게 구체적으로 명시하는 편이 좋음
 *
 * 기본적으로 스프링은 @Configuration, @Bean, @Component, @Repository, @Service, @Controller가 달린 클래스들을 모두 감지
 * 이 때, 하나 이상의 포함/제외 필터를 적용해 스캐닝 과정을 커스터마이징 할 수 있음
 * 아래와 같이 하면, com.example.study_spring.Ch02 패키지에 속한 클래스 중 Dao와 Service가 들어간 이름의 클래스는 모두 넣고
 * @Controller를 붙인 클래스를 빼게 됨
 * 이와 같이 애너테이션이 달려 있지 않은 클래스도 스프링이 자동 감지하게 할 수 있음
 */

@ComponentScan(
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = {"com.example.study_spring.Ch02.*Dao",
                                "com.example.study_spring.Ch02.*Service"
                        }
                )
        },
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        classes = {org.springframework.stereotype.Controller.class}
                )
        }
)
@Component("sequenceDao")
public class SequenceDaoImpl implements SequenceDao {
    private final Map<String, Sequence> sequences = new HashMap<>();
    private final Map<String, AtomicInteger> values = new HashMap<>();

    public SequenceDaoImpl() {
        sequences.put("IT", new Sequence("IT", "30", "A"));
        values.put("IT", new AtomicInteger(10000));
    }

    @Override
    public Sequence getSequence(String sequenceId) {
        return sequences.get(sequenceId);
    }

    @Override
    public int getNextValue(String sequenceId) {
        AtomicInteger value = values.get(sequenceId);
        return value.getAndIncrement();
    }
}
