package com.omakase.omastay.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.omakase.omastay.dto.custom.CancelRequest;

import org.springframework.http.MediaType;

@Controller
@RequestMapping("/payment")
public class PaymentController {


    @PostMapping("/cancel")
    public ResponseEntity<String> cancelPayment(@RequestBody CancelRequest cancelRequest) {
        String paymentKey = cancelRequest.getPaymentKey();
          // URL 생성 (UriComponentsBuilder 사용)
        String url = UriComponentsBuilder.fromHttpUrl("https://api.tosspayments.com/v1/payments/{paymentKey}/cancel")
                .buildAndExpand(paymentKey)
                .toUriString();

        // HTTP 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic dGVzdF9za192Wm5qRUplUVZ4TE5vZUJCMm03RFZQbU9vQk4wOg==");
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 취소 요청 바디 설정
        String cancelReason = "{\"cancelReason\":\""+cancelRequest.getCancelReason()+"\"}";

        HttpEntity<String> entity = new HttpEntity<>(cancelReason, headers);

        // RestTemplate을 사용해 외부 API 호출
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    
}
