package com.example.study_spring.Ch02;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 타입을 기준으로 자동 연결하면 IoC 컨테이너에 호환 타입이 여럿 존재하거나 프로퍼티가 그룹형이 아닐 경우 제대로 연결 되지 않음
 * 타입이 같은 빈이 여럿이라면 @Primary, @Qualifier로 해결할 수 있음
 *
 * @Primary -> 후보 빈을 명시, 여러 빈이 자동 연결 대상일 때 특정 빈에 우선권 부여
 */

@Component
@Primary
public class DatePrefixGenerator implements PrefixGenerator {
    private DateFormat formatter;

    public void setPattern(String pattern) {
        this.formatter = new SimpleDateFormat(pattern);
    }

    @Override
    public String getPrefix() {
        return formatter.format(new Date());
    }
}
