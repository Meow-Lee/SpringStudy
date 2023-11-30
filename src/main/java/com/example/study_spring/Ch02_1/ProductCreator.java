package com.example.study_spring.Ch02_1;

import java.io.IOException;
import java.util.Map;

public class ProductCreator {
    /**
     * 정적 팩토리 메서드로 POJO 생성
     * 주어진 productId에 따라 인스턴스화할 실제 상품 클래스를 내부 로직으로 결정
     */
//    public static Product createProduct(String productId) {
//        if ("aaa".equals(productId)) {
//            return new Battery("AAA", 2.5);
//        } else if ("cdrw".equals(productId)) {
//            return new Disc("CD-RW", 1.5);
//        } else if ("dvdrw".equals(productId)) {
//            return new Disc("DVD-RW", 3.0);
//        }
//        throw new IllegalArgumentException("Unknown product");
//    }

    /**
     * 인스턴스 팩토리 메서드
     */
    private Map<String, Product> products;

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public Product createProduct(String productId) {
        Product product = products.get(productId);
        if (product != null) {
            return product;
        }
        throw new IllegalArgumentException("Unknown product");
    }
}
