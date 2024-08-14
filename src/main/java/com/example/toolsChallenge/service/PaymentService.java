package com.example.toolsChallenge.service;

import com.example.toolsChallenge.domain.Description;
import com.example.toolsChallenge.domain.Transaction;
import com.example.toolsChallenge.dto.ReceivePaymentDTO;
import com.example.toolsChallenge.dto.ResponsePaymentDTO;
import com.example.toolsChallenge.mapper.DescriptionMapper;
import com.example.toolsChallenge.mapper.TransactionMapper;
import com.example.toolsChallenge.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final DescriptionMapper descriptionMapper;

    private final TransactionMapper transactionMapper;

    private final TransactionRepository transactionRepository;

    public ResponsePaymentDTO makePayment(ReceivePaymentDTO receivePaymentDTO){
        Random random = new Random();
        Description description = descriptionMapper.toEntity(receivePaymentDTO.getDescricao());
        description.setNsu(String.format("%06d",random.nextInt(999999)));
        description.setCodigoAutorizacao(String.format("%06d",random.nextInt(999999)));
        if (description.getValor() > 1000){
            description.setStatus("NEGADO");
        }
        if (description.getValor() > 0 && description.getValor() <= 1000){
            description.setStatus("AUTORIZADO");
        }
        Transaction transaction = transactionMapper.toEntity(receivePaymentDTO);
        if (receivePaymentDTO.getTransacao().getCartao().length() != 16){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cartão de crédito inválido");
        }
        transaction.setCartao(receivePaymentDTO.getTransacao().getCartao());
        transaction.setDescricao(description);
         return transactionMapper.toDtoResponse(transactionRepository.save(transaction));
    }

    public ResponsePaymentDTO reversalBuy(Integer id){
        Transaction transaction = transactionRepository.findById(id).orElse(null);
        Description description = transaction.getDescricao();
        description.setStatus("CANCELADO");
        return transactionMapper.toDtoResponse(transactionRepository.save(transaction));
    }

    public ResponsePaymentDTO findById(Integer id){
        return transactionMapper.toDtoResponse(transactionRepository.findById(id).orElse(null));
    }

    public List<ResponsePaymentDTO> findAll(){
        return transactionMapper.toDtoList(transactionRepository.findAll());
    }
}
