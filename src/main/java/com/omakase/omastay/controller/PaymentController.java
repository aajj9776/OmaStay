package com.omakase.omastay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.omakase.omastay.dto.PaymentDTO;
import com.omakase.omastay.dto.ReservationDTO;
import com.omakase.omastay.dto.custom.CancelRequestDTO;
import com.omakase.omastay.service.PaymentService;
import com.omakase.omastay.service.ReservationService;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ReservationService reservationService;


    @PostMapping("/cancel")
    public ResponseEntity<String> cancelPayment(@RequestBody CancelRequestDTO cancelRequest) {
        String paymentKey = cancelRequest.getPaymentKey();
        System.out.println("키"+ paymentKey);
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
        if(response.getStatusCode() == HttpStatus.OK){
            System.out.println("취소 성공");
            PaymentDTO paymentDTO = paymentService.getPayment(cancelRequest);
            if( paymentDTO != null){
                ReservationDTO reservationDTO = reservationService.getReservation(cancelRequest.getResIdx());
            }

        }else{
            System.out.println("취소 실패");
        }
        
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    
}
