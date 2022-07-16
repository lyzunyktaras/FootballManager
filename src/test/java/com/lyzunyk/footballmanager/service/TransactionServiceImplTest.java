package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.converter.ResponseConverter;
import com.lyzunyk.footballmanager.dto.player.PlayerResponse;
import com.lyzunyk.footballmanager.dto.transaction.TransactionResponse;
import com.lyzunyk.footballmanager.dto.transfer.TransferDetailsDto;
import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Player;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.model.Wallet;
import com.lyzunyk.footballmanager.repository.TransactionRepository;
import com.lyzunyk.footballmanager.service.impl.TransactionServiceImpl;
import com.lyzunyk.footballmanager.strategy.ProcessPaymentStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceImplTest {

    private static final String EXISTING_ID = "ID";

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private Transaction transaction;
    @Mock
    private ResponseConverter responseConverter;
    @Mock
    private TransactionResponse transactionResponse;
    @Mock
    private ProcessPaymentStrategy processPaymentStrategy;
    @Mock
    private TransferDetailsDto transferDetailsDto;
    @Mock
    private WalletService walletService;

    @InjectMocks
    private TransactionServiceImpl testingInstance;

    @Test
    public void shouldFindTransactionById() {
        when(transactionRepository.findTransactionById(any())).thenReturn(transaction);

        Transaction result = testingInstance.findTransactionById(EXISTING_ID);

        assertEquals(transaction, result);
    }

    @Test(expected = NotExistException.class)
    public void shouldThrowNotExistExceptionWhenTransactionFoundByIdIsNull() {
        when(transactionRepository.findTransactionById(any())).thenReturn(null);

        testingInstance.findTransactionById(EXISTING_ID);
    }

    @Test
    public void shouldFindAllTransactions() {
        when(transactionRepository.findAll()).thenReturn(Collections.singletonList(transaction));
        when(responseConverter.convertToTransactionResponse(transaction)).thenReturn(transactionResponse);

        List<TransactionResponse> result = testingInstance.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(transactionResponse, result.get(0));
    }

    @Test
    public void shouldProcessPayment(){
        Club seller = mock(Club.class);
        Club buyer = mock(Club.class);
        Wallet sellerWallet = mock(Wallet.class);
        Wallet buyerWallet = mock(Wallet.class);

        when(processPaymentStrategy.processTransaction(transferDetailsDto)).thenReturn(transaction);
        when(transferDetailsDto.getSeller()).thenReturn(seller);
        when(transferDetailsDto.getBuyer()).thenReturn(buyer);
        when(seller.getWallet()).thenReturn(sellerWallet);
        when(buyer.getWallet()).thenReturn(buyerWallet);

        testingInstance.processPayment(transferDetailsDto);

        verify(transactionRepository).save(transaction);

        verify(walletService).addTransactionToWallet(transaction,sellerWallet);
        verify(walletService).addTransactionToWallet(transaction,buyerWallet);
    }
}
