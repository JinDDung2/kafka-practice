package com.example.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class CheckoutFormController {

    @GetMapping("/checkout-form")
    public String checkOutForm(Model model) {
        log.info("checkoutForm...");
        return "checkoutForm";
    }

}
