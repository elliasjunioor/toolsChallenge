package com.example.toolsChallenge.controller;


import com.example.toolsChallenge.dto.ReceivePaymentDTO;
import com.example.toolsChallenge.dto.ResponsePaymentDTO;
import com.example.toolsChallenge.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<ResponsePaymentDTO> makePayment(@RequestBody ReceivePaymentDTO receivePaymentDTO){
        return ResponseEntity.ok(paymentService.makePayment(receivePaymentDTO));
    }

    @PutMapping
    public ResponseEntity<ResponsePaymentDTO> reversalBuy(@RequestParam Integer transactionId){
        return ResponseEntity.ok(paymentService.reversalBuy(transactionId));
    }

    @GetMapping("/id")
    public ResponseEntity<ResponsePaymentDTO> findById(@RequestParam Integer transactionId){
        return ResponseEntity.ok((paymentService.findById(transactionId)));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponsePaymentDTO>> findAll(){
        return ResponseEntity.ok(paymentService.findAll());
    }

}
