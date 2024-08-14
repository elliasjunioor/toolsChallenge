import com.example.toolsChallenge.domain.Description;
import com.example.toolsChallenge.domain.Transaction;
import com.example.toolsChallenge.dto.*;
import com.example.toolsChallenge.mapper.DescriptionMapper;
import com.example.toolsChallenge.mapper.TransactionMapper;
import com.example.toolsChallenge.repository.TransactionRepository;
import com.example.toolsChallenge.service.PaymentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;


class PaymentServiceTest {

    @Mock
    private DescriptionMapper descriptionMapper;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testMakePaymentAbove10() {
        ReceivePaymentDTO receivePaymentDTO = new ReceivePaymentDTO();
        Description description = new Description();
        description.setValor(1500.0); // Valor maior que 1000
        Mockito.when(descriptionMapper.toEntity(Mockito.any())).thenReturn(description);
        receivePaymentDTO.setTransacao(new TranscationDTO("4001********1234", 1));
        Transaction transaction = new Transaction();
        Mockito.when(transactionMapper.toEntity(Mockito.any())).thenReturn(transaction);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
        Mockito.when(transactionMapper.toDtoResponse(Mockito.any())).thenReturn(new ResponsePaymentDTO());
        ResponsePaymentDTO response = paymentService.makePayment(receivePaymentDTO);
        Assertions.assertEquals("NEGADO", description.getStatus());
        Mockito.verify(transactionRepository).save(transaction);
    }

    @Test
    void testMakePaymentBelow1000() {
        ReceivePaymentDTO receivePaymentDTO = new ReceivePaymentDTO();
        Description description = new Description();
        description.setValor(500.0);
        Mockito.when(descriptionMapper.toEntity(Mockito.any())).thenReturn(description);
        receivePaymentDTO.setTransacao(new TranscationDTO("4001********1234", 1));
        Transaction transaction = new Transaction();
        Mockito.when(transactionMapper.toEntity(Mockito.any())).thenReturn(transaction);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
        Mockito.when(transactionMapper.toDtoResponse(Mockito.any())).thenReturn(new ResponsePaymentDTO());
        ResponsePaymentDTO response = paymentService.makePayment(receivePaymentDTO);
        Assertions.assertEquals("AUTORIZADO", description.getStatus());
        Mockito.verify(transactionRepository).save(transaction);
    }

    @Test
    void testMakePaymentException() {
        ReceivePaymentDTO receivePaymentDTO = new ReceivePaymentDTO();
        Description description = new Description();
        description.setValor(500.0);
        Mockito.when(descriptionMapper.toEntity(Mockito.any())).thenReturn(description);
        receivePaymentDTO.setTransacao(new TranscationDTO("4001********123", 1));
        Transaction transaction = new Transaction();
        Mockito.when(transactionMapper.toEntity(Mockito.any())).thenReturn(transaction);
        Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(transaction);
        Mockito.when(transactionMapper.toDtoResponse(Mockito.any())).thenReturn(new ResponsePaymentDTO());
        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
            paymentService.makePayment(receivePaymentDTO);
        });
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatusCode());

    }

    @Test
    void testReversalBuy() {
        Description description = new Description();
        description.setStatus("APROVADO");
        Transaction transaction = new Transaction();
        transaction.setDescricao(description);
        ResponsePaymentDTO responsePaymentDTO = new ResponsePaymentDTO();
        Mockito.when(transactionRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(transaction));
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);
        Mockito.when(transactionMapper.toDtoResponse(transaction)).thenReturn(responsePaymentDTO);
        ResponsePaymentDTO result = paymentService.reversalBuy(1);
        Assertions.assertEquals("CANCELADO", transaction.getDescricao().getStatus(), "O status da descrição deve ser CANCELADO");
        Assertions.assertEquals(responsePaymentDTO, result, "O DTO de resposta deve ser igual ao esperado");
        Mockito.verify(transactionRepository).findById(1);
        Mockito.verify(transactionRepository).save(transaction);
        Mockito.verify(transactionMapper).toDtoResponse(transaction);
    }

    @Test
    public void testFindById() {
        Transaction transaction = new Transaction();
        ResponsePaymentDTO responsePaymentDTO = new ResponsePaymentDTO();
        Mockito.when(transactionRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(transaction));
        Mockito.when(transactionMapper.toDtoResponse(transaction)).thenReturn(responsePaymentDTO);
        ResponsePaymentDTO result = paymentService.findById(1);
        Assertions.assertEquals(responsePaymentDTO, result);
        Mockito.verify(transactionRepository).findById(1);
        Mockito.verify(transactionMapper).toDtoResponse(transaction);
    }

    @Test
    public void testFindAll() {
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);
        ResponsePaymentDTO dto1 = new ResponsePaymentDTO();
        ResponsePaymentDTO dto2 = new ResponsePaymentDTO();
        List<ResponsePaymentDTO> responsePaymentDTOs = Arrays.asList(dto1, dto2);
        Mockito.when(transactionRepository.findAll()).thenReturn(transactions);
        Mockito.when(transactionMapper.toDtoList(transactions)).thenReturn(responsePaymentDTOs);
        List<ResponsePaymentDTO> result = paymentService.findAll();
        Assertions.assertEquals(responsePaymentDTOs, result);
        Mockito.verify(transactionRepository).findAll();
        Mockito.verify(transactionMapper).toDtoList(transactions);
    }

}
