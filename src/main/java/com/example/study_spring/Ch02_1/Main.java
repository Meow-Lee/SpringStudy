package com.example.study_spring.Ch02_1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * 자바 구성 클래스에서 생성자로 만든 POJO 인스턴스를 IoC 컨테이너에 빈으로 등록이 되고, IoC 컨테이너를 인스턴스화하여 정보를 가져올 수 있음
 *
 * BannerLoader 클래스의 @PostConstruct로 인해서 IoC 컨테이너 구성 시점에 배너가 출력이 되고, 그 이후 Main에서의 출력들이 나옴
 */

public class Main {
    public static void main(String[] args) throws IOException {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ShopConfiguration.class);

        Product aaa = ac.getBean("aaa", Product.class);
        Product cdrw = ac.getBean("cdrw", Product.class);
        Product dvdrw = ac.getBean("dvdrw", Product.class);

//        System.out.println(aaa);
//        System.out.println(cdrw);

        /**
         * AAA 2.5
         * CD-RW 1.5
         */

        /**
         * 스프링 기본 스코프가 singleton이므로 두 고객이 동일한 카트 인스턴스를 공유함
         * 하지만, getBean() 메소드 호출 시 쇼핑몰 방문 고객마다 상이한 카트 인스턴스를 가져와야 맞는것
         * shoppingCart 빈 스코프를 prototype으로 설정하면 getBean() 메서드를 호출할 때마다 빈 인스턴스를 새로 만듬
         */

        ShoppingCart cart1 = ac.getBean("shoppingCart", ShoppingCart.class);
        cart1.addItem(aaa);
        cart1.addItem(cdrw);
        System.out.println("Shopping Cart 1 contains " + cart1.getItems());

        ShoppingCart cart2 = ac.getBean("shoppingCart", ShoppingCart.class);
        cart2.addItem(dvdrw);
        System.out.println("Shopping cart 2 contains " + cart2.getItems());

        /**
         * Shopping Cart 1 contains [AAA 2.5, CD-RW 1.5]
         * Shopping cart 2 contains [AAA 2.5, CD-RW 1.5, DVD-RW 3.0]
         *
         * prototype으로 변경 후 결과
         * Shopping Cart 1 contains [AAA 2.5, CD-RW 1.5]
         * Shopping cart 2 contains [DVD-RW 3.0]
         */

        Resource resource = new ClassPathResource("discounts.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);

        System.out.println("And don't forget our discounts!");
        System.out.println(props);

        /**
         * ***********************
         * * Welcome to My Shop! *
         * ***********************
         * Shopping Cart 1 contains [AAA 2.5, CD-RW 1.5]
         * Shopping cart 2 contains [DVD-RW 3.0]
         * And don't forget our discounts!
         * {specialcustomer.discount=0.1, endofyear.discount=0.2, summer.discount=0.15}
         */


        /**
         * getMessage() 메서드로 메시지를 해석할 수 있음
         * 첫번째 인수는 메시지 키, 두번째 인수는 메시지 매개변수 배열, 세번째 인수는 대상 로케일
         * alert 변수에는 매개변수가 없으므로 null, alert_inventory에는 매개변수가 있으므로 각각의 메시지 매개변수 자리에 끼워넣을 값을 할당
         */
        String alert = ac.getMessage("alert.checkout", null, Locale.US);
        String alert_inventory = ac.getMessage("alert.inventory.checkout",
                new Object[]{"[DVD-RW 3,0", new Date()}, Locale.US);

        System.out.println("The I18N message for alert.checkout is: " + alert);
        System.out.println("The I18N message for alert.inventory.checkout is: " + alert_inventory);

        /**
         * The I18N message for alert.checkout is: A shopping cart has been checked out.
         * The I18N message for alert.inventory.checkout is: A shopping cart with [DVD-RW 3,0 has been checked out at 11/29/23, 3:11 PM.
         */
    }
}
