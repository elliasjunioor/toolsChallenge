package com.example.toolsChallenge.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="PAYMENT")
public class PaymentMethod {

    @Id
    @GeneratedValue(generator = "PaymentSq")
    @SequenceGenerator(name= "PaymentSq", sequenceName = "ID_PAYMENT_SQ", allocationSize = 1)
    @Column(name = "ID_PAYMENT", nullable = false)
    private Integer id;

    @Column(name = "TYPE")
    private String tipo;

    @Column(name = "INSTALLMENT")
    private Integer parcelas;


}
