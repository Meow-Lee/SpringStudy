package com.example.study_spring.Ch02_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

@Component
public class Cashier {
    @Autowired
    private MessageSource messageSource;

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Main 클래스는 애플리케이션 컨텍스트를 직접 가져올 수 있으므로 텍스트 메시지를 해석할 수 있지만
     * 텍스트 메시지를 해석하는 빈에는 MessageSource 구현체를 넣어야 함
     */

    public void checkout(ShoppingCart cart) throws IOException {
        String alert = messageSource.getMessage("alert.invectory.checkout",
                new Object[]{cart.getItems(), new Date()}, Locale.US);
        System.out.println(alert);
    }
}
