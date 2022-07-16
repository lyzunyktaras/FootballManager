package com.lyzunyk.footballmanager.service;

import com.lyzunyk.footballmanager.exception.NotExistException;
import com.lyzunyk.footballmanager.model.Club;
import com.lyzunyk.footballmanager.model.Transaction;
import com.lyzunyk.footballmanager.model.Wallet;
import com.lyzunyk.footballmanager.repository.WalletRepository;
import com.lyzunyk.footballmanager.service.impl.WalletServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WalletServiceImplTest {

    private static final String EXISTING_ID = "ID";
    private static final BigDecimal EXISTING_TOTAL = BigDecimal.valueOf(1.0);
    private static final BigDecimal EXISTING_BUYER_TOTAL = BigDecimal.valueOf(2.0);
    private static final BigDecimal EXISTING_SELLER_TOTAL = BigDecimal.valueOf(1.0);

    @Mock
    private Wallet wallet;
    @Mock
    private WalletRepository walletRepository;
    @Mock
    private Club club;
    @Mock
    private Club seller;
    @Mock
    private Club buyer;
    @Mock
    private Wallet sellerWallet;
    @Mock
    private Wallet buyerWallet;
    @Mock
    private Transaction transaction;


    @InjectMocks
    private WalletServiceImpl testingInstance;

    @Test
    public void shouldFindWalletById() {
        when(walletRepository.findWalletById(any())).thenReturn(wallet);

        Wallet result = testingInstance.findWalletById("");

        assertEquals(wallet, result);
    }

    @Test(expected = NotExistException.class)
    public void shouldThrowNotExistExceptionWhenWalletFoundByIdIsNull() {
        when(walletRepository.findWalletById(any())).thenReturn(null);

        testingInstance.findWalletById(EXISTING_ID);
    }

    @Test
    public void shouldAddWallet() {
        Wallet result = testingInstance.addWallet(club, EXISTING_TOTAL.floatValue());

        verify(walletRepository).save(any(Wallet.class));

        assertEquals(EXISTING_TOTAL, result.getTotal());
    }

    @Test
    public void shouldDeleteWallet() {
        testingInstance.deleteWallet(wallet);

        verify(walletRepository).delete(wallet);

        assertNull(walletRepository.findWalletById(wallet.getId()));
    }

    @Test
    public void shouldProcessPayment() {
        when(seller.getWallet()).thenReturn(sellerWallet);
        when(buyer.getWallet()).thenReturn(buyerWallet);
        when(buyerWallet.getTotal()).thenReturn(EXISTING_BUYER_TOTAL);
        when(sellerWallet.getTotal()).thenReturn(EXISTING_SELLER_TOTAL);

        testingInstance.processPayment(seller, buyer, EXISTING_TOTAL.floatValue());

        verify(walletRepository).save(sellerWallet);
        verify(walletRepository).save(buyerWallet);
    }

    @Test
    public void shouldAddTransactionsToWallet() {
        when(wallet.getTransactions()).thenReturn(Sets.newSet(transaction));

        testingInstance.addTransactionToWallet(transaction, wallet);

        verify(walletRepository).save(wallet);
    }

}
