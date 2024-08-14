package com.example.toolsChallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceivePaymentDTO {
    private TranscationDTO transacao;

    private ReceiveDescriptionDTO descricao;

    private PaymentMethodDTO formaPagamento;
}
