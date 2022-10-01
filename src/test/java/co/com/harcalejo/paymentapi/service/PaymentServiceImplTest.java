package co.com.harcalejo.paymentapi.service;

import co.com.harcalejo.paymentapi.entity.Payment;
import co.com.harcalejo.paymentapi.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentServiceImplTest {

    @MockBean
    PaymentRepository paymentRepository;

    private PaymentService paymentService;

    @BeforeEach
    void setup() {
        paymentService =
                new PaymentServiceImpl(paymentRepository);
    }

    @Test
    void shouldReturnPaymentsLoan() {
        //given
        Long loanId = 1L;

        Payment paymentA = new Payment();
        paymentA.setId(220L);
        paymentA.setLoanId(loanId);
        paymentA.setAmount(3352.24);
        paymentA.setRegisterDate(LocalDate.now());

        Payment paymentB = new Payment();
        paymentB.setId(385L);
        paymentB.setLoanId(loanId);
        paymentB.setAmount(8885.07);
        paymentB.setRegisterDate(LocalDate.now());

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(paymentA);
        paymentList.add(paymentB);

        //when
        when(paymentRepository.findByLoanId(loanId))
                .thenReturn(paymentList);

        //then
        assertThat(paymentService.getPaymentsLoan(loanId))
                .isNotEmpty();

        assertThat(paymentService
                .getPaymentsLoan(loanId).get(0).getLoanId())
                .isEqualTo(loanId);
    }
}