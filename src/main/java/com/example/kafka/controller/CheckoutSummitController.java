package com.example.kafka.controller;

import com.example.kafka.dto.CheckoutDto;
import com.example.kafka.service.CheckoutWriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CheckoutSummitController {

    private final CheckoutWriteService checkoutWriteService;

    @PostMapping("/summitCheckout")
    public String summitCheckout(CheckoutDto checkoutDto, Model model) {

        log.info(checkoutDto.toString());
        Long checkoutId = checkoutWriteService.saveCheckoutData(checkoutDto);
        model.addAttribute("checkoutId", checkoutId);
        return "summitComplete";
    }
}
