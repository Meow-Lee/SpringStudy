package com.example.study_spring.Ch02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        /**
         * IoC 컨테이너는 기본 구현체인 BeanFactory와 고급 구현체인 ApplicationContext 두 종류가 있음
         * 리소스에 제약이 있지 않으면 ApplicationContext를 사용
         * 둘 모두 인터페이스로, ApplicationContext는 BeanFactory의 하위 인터페이스로 호환 가능
         * IoC 컨테이너를 인스턴스화 해야 Annotation이 있는 메서드들을 탐색할 수 있고 빈 인스턴스를 가져올 수 있음
         * 따라서, 구현체인 AnnotationConfigApplicationContext(Configuration 클래스)를 사용하여 인스턴스화
         * 이후, 객체 레퍼런스(ac)는 POJO 인스턴스 또는 빈에 액세스하는 창구 역할을 함
         *
         * getBean() 메서드는 Object타입을 반환하기 때문에 실제 타입에 맞게 캐스팅해야 함
         * 만약, 캐스팅을 하고 싶지 않다면 getBean() 메서드 두 번째 인수에 빈 클래스명을 지정
         * 빈이 하나 뿐이라면 이름 생략 가능
         */
//        ApplicationContext ac = new AnnotationConfigApplicationContext(SequenceGeneratorConfiguration.class);
//
//        SequenceGenerator generator = (SequenceGenerator) ac.getBean("sequenceGenerator");
//        SequenceGenerator generator = ac.getBean("sequenceGenerator", SequenceGenerator.class);
//        SequenceGenerator generator = ac.getBean(SequenceGenerator.class);
//
//        System.out.println(generator.getSequence());
//        System.out.println(generator.getSequence());

        /**
         * 301000000A
         * 301000001A
         */

        /**
         * package 명으로 applicationcontext를 인스턴스화 한 뒤에, sequenceDao로 등록한 sequenceDaoImpl 구현체 빈을 가져옴
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext("com.example.study_spring.Ch02");

        SequenceDao sequenceDao = ac.getBean(SequenceDao.class);

        System.out.println(sequenceDao.getNextValue(("IT")));
        System.out.println(sequenceDao.getNextValue(("IT")));

        /**
         * 10000
         * 10001
         */
    }
}
