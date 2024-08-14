package com.example.toolsChallenge.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="DESCRIPTION")
public class Description {

    @Id
    @GeneratedValue(generator = "DescriptionSq")
    @SequenceGenerator(name= "DescriptionSq", sequenceName = "ID_DESCRIPTION_SQ", allocationSize = 1)
    @Column(name = "ID_DESCRIPTION", nullable = false)
    private Integer id;

    @Column(name = "VALUEBUY")
    private Double valor;

    @Column(name = "DATEHOUR", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataHora;

    @Column(name = "ESTABLISHMENT")
    private String estabelecimento;

    @Column(name = "NSU")
    private String nsu;

    @Column(name = "AUTHORIZATIONCODE")
    private String codigoAutorizacao;

    @Column(name = "STATUS")
    private String status;

}
