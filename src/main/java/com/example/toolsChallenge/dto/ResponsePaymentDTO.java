package com.example.toolsChallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePaymentDTO {

    private String cartao;

    private Integer id;

    private ResponseDescriptionDTO descricao;

    private PaymentMethodDTO formaPagamento;

}
