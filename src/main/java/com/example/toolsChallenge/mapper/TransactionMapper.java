package com.example.toolsChallenge.mapper;

import com.example.toolsChallenge.domain.Transaction;
import com.example.toolsChallenge.dto.ReceivePaymentDTO;
import com.example.toolsChallenge.dto.ResponsePaymentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    Transaction toEntity(ReceivePaymentDTO receivePaymentDTO);

    ResponsePaymentDTO toDtoResponse(Transaction transaction);

    List<ResponsePaymentDTO> toDtoList(List<Transaction> transactionList);
}
