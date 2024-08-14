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
@Table(name="TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(generator = "TransactionSq")
    @SequenceGenerator(name= "TransactionSq", sequenceName = "ID_TRANSACTION_SQ", allocationSize = 1)
    @Column(name = "ID_TRANSACTION", nullable = false)
    private Integer id;

    @Column(name = "CARD")
    private String cartao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name= "ID_DESCRIPTION", referencedColumnName = "ID_DESCRIPTION", updatable = false)
    private Description descricao;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name= "ID_PAYMENT", referencedColumnName = "ID_PAYMENT", updatable = false)
    private PaymentMethod formaPagamento;


}
