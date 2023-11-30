package com.example.study_spring.Ch02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 서비스 객체를 생성하는 서비스 클래스는 자주 쓰이는 모범 사례로, DAO를 직접 호출하는 대신 일종의 퍼사드(관문, facade)를 두는 것
 * 서비스 객체는 내부적으로 DAO와 연동하며 시퀀스 생성 요청을 처리
 *
 * @Component를 붙였기 때문에 스프링 빈으로 등록됨
 * 그리고 @Autowired를 사용하믕로서 sequenceDao 빈이 해당 프로퍼티에 자동 연결됨
 * 배열형 프로퍼티에 @Autowired를 붙이면 스프링은 매치된 빈을 모두 찾아 자동 연결
 * ex) @Autowired private PrefixGenerator[] prefixGenerators;
 * IoC 컨테이너에 선언된 타입 호환되는 빈이 여럿 있으면 자동으로 추가
 * 컬렉션에도 가능
 * ex) @Autowired private List<PrefixGenerator> prefixGenerators;
 * ex) @Autowired private Map<String, PrefixGenerator> prefixGenerators;
 */

//@Component
@Service
public class SequenceService {
    @Autowired
    private SequenceDao sequenceDao;

    /**
     * 생성자에도 @Autowired를 붙여 자동 연결할 수 있음
     * 생성자 인수가 몇 개든 각 인수형과 호환되는 빈을 연결
     */
    @Autowired
    public void setSequenceDao(SequenceDao sequenceDao) {
        this.sequenceDao = sequenceDao;
    }

    public String generate(String sequenceId) {
        Sequence sequence = sequenceDao.getSequence(sequenceId);
        int value = sequenceDao.getNextValue(sequenceId);
        return sequence.getPrefix() + value + sequence.getSuffix();
    }
}
