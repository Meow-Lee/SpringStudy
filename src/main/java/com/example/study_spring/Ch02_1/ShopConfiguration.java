package com.example.study_spring.Ch02_1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * IoC 컨테이너에서 생성자를 호출하여 POJO 인스턴스/빈을 생성
 * 생성자 호출은 스프링에서 빈을 생성하는 가장 일반적이면서 직접적인 방법
 * 자바 new 연산자로 객체를 생성하는 것과 같음
 * <p>
 * 자바 구성 클래스에서 IoC 컨테이너가 사용할 POJO 인스턴스 값을 생성자로 설정한 다음, IoC 컨테이너를 인스턴스화하여 애너테이션을 붙인 클래스를 스캐닝
 * 생성자로 POJO 인스턴스/빈을 생성하는 자바 구성 클래스 작성
 *
 * @PropertySource를 사용하여 빈 프로퍼티 구성 전용 프로퍼티 파일의 내용을 읽을 수 있음
 * 스프링은 자바 클래스패스에서 discounts.properties 파일을 찾음
 * @PropertySource를 붙여 프로퍼티 파일을 로드하려면 PropertySourcePlaceholderConfigurer 빈을 @Bean으로 선언해야함
 */
@Configuration
@PropertySource("classpath:discounts.properties")

@ComponentScan("com.example.study_spring.Ch02_1")
public class ShopConfiguration {
    @Value("classpath:banner.txt")
    private Resource banner;

    @Value("${endofyear.discount:0}")
    private double specialEndofyearDiscountField;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BannerLoader bannerLoader() {
        BannerLoader bl = new BannerLoader();
        bl.setBanner(banner);
        return bl;
    }

//    @Bean
//    public Product aaa() {
//        Battery p1 = new Battery("AAA", 2.5);
//        p1.setRechargeable(true);
//        return p1;
//    }
//
//    @Bean
//    public Product cdrw() {
//        Disc p2 = new Disc("CD-RW", 1.5);
//        p2.setCapacity(700);
//        return p2;
//    }
//
//    @Bean
//    public Product dvdrw() {
//        Disc p2 = new Disc("DVD-RW", 3.0);
//        p2.setCapacity(700);
//        return p2;
//    }

    /**
     * 리소스 번들 메시지를 구분 처리하려면 ReloadableResourceBundleMessageSource 빈 인스턴스를 자바 구성 파일에 정의
     * messages로 이름이 시작되는 파일들을 찾도록 설정
     * 캐시 주기는 1초로 설정해서 쓸모없는 메시지를 다시 읽지 않게 함
     * 캐시를 갱신할 때는 실제로 프로퍼티 파일을 읽기 전 최종 수정 타임스탬프 이후의 변경사항이 있는지 살펴보고, 없으면 다시 읽지 않음
     */

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages");
        messageSource.setCacheSeconds(1);
        return messageSource;
    }

    /**
     * 일반 자바 구문으로 정적 팩토리 메서드를 호출해 POJO를 생성
     */
//    @Bean
//    public Product aaa() {
//        return ProductCreator.createProduct("aaa");
//    }
//
//    @Bean
//    public Product cdrw() {
//        return ProductCreator.createProduct("cdrw");
//    }
//
//    @Bean
//    public Product dvdrw() {
//        return ProductCreator.createProduct("dvdrw");
//    }

    /**
     * 인스턴스 팩토리 메서드로 POJO 생성
     */
//    @Bean
//    public ProductCreator productCreatorFactory() {
//        ProductCreator factory = new ProductCreator();
//        Map<String, Product> products = new HashMap<>();
//
//        products.put("aaa", new Battery("AAA", 2.5));
//        products.put("cdrw", new Disc("CD-RW", 1.5));
//        products.put("dvdrw", new Disc("DVD-RW", 3.0));
//        factory.setProducts(products);
//        return factory;
//    }
//
//    @Bean
//    public Product aaa() {
//        return productCreatorFactory().createProduct("aaa");
//    }
//
//    @Bean
//    public Product cdrw() {
//        return productCreatorFactory().createProduct("cdrw");
//    }
//
//    @Bean
//    public Product dvdrw() {
//        return productCreatorFactory().createProduct("dvdrw");
//    }

    /**
     * 스프링 팩토리 빈
     * IoC 컨테이너 안에서 다른 빈을 찍어내는 공장 역할을 하며, 개념은 팩토리 메서드와 비슷하지만 빈 생성 도중 IoC 컨테이너가 식별할 수 있는 스프링 전용 빈
     */
    @Bean
    public Battery aaa() {
        Battery aaa = new Battery("AAA", 2.5);
        return aaa;
    }

    @Bean
    public Disc cdrw() {
        Disc aaa = new Disc("CD-RW", 1.5);
        return aaa;
    }

    @Bean
    public Disc dvdrw() {
        Disc aaa = new Disc("DVD-RW", 3.0);
        return aaa;
    }

    @Bean
    public DiscountFactoryBean discountFactoryBeanAAA() {
        DiscountFactoryBean factory = new DiscountFactoryBean();
        factory.setProduct(aaa());
        factory.setDiscount(0.2);
        return factory;
    }
    @Bean
    public DiscountFactoryBean discountFactoryBeanCDRW() {
        DiscountFactoryBean factory = new DiscountFactoryBean();
        factory.setProduct(cdrw());
        factory.setDiscount(0.1);
        return factory;
    }
    @Bean
    public DiscountFactoryBean discountFactoryBeanDVDRW() {
        DiscountFactoryBean factory = new DiscountFactoryBean();
        factory.setProduct(dvdrw());
        factory.setDiscount(0.1);
        return factory;
    }
}
