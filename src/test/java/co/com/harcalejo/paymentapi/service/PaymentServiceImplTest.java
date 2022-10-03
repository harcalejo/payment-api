package co.com.harcalejo.paymentapi.service;

import co.com.harcalejo.paymentapi.config.PaymentProperties;
import co.com.harcalejo.paymentapi.dto.DebtBalanceDTO;
import co.com.harcalejo.paymentapi.entity.Payment;
import co.com.harcalejo.paymentapi.exception.PaymentException;
import co.com.harcalejo.paymentapi.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PaymentServiceImplTest {

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private PaymentProperties paymentProperties;

    private PaymentService paymentService;

    @BeforeEach
    void setup() {
        paymentService =
                new PaymentServiceImpl(
                        paymentRepository,
                        paymentProperties, restTemplate);
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

    @Test
    void shouldRegisterPaymentLoan() {
        //given
        Long loanId = 1L;
        double amount = 10.00;

        DebtBalanceDTO debtBalanceDTO_before =
                new DebtBalanceDTO();
        debtBalanceDTO_before.setBalance(20.00);

        DebtBalanceDTO debtBalanceDTO_after =
                new DebtBalanceDTO();
        debtBalanceDTO_after.setBalance(10.00);

        Payment payment = new Payment();
        payment.setLoanId(loanId);
        payment.setAmount(amount);
        payment.setRegisterDate(LocalDate.now());

        //when
        when(restTemplate
                .getForEntity(
                        paymentProperties
                                .getDebtApiUrl() +"/1", DebtBalanceDTO.class))
                .thenReturn(new ResponseEntity<>(debtBalanceDTO_before, HttpStatus.OK),
                        new ResponseEntity<>(debtBalanceDTO_after, HttpStatus.OK));
        when(paymentRepository.save(any()))
                .thenReturn(payment);

        //then
        assertThat(paymentService.registerPayment(loanId, amount)
                .getDebt()).isEqualTo(10.00);
    }

    @Test
    void shouldFailRegisterPaymentGreaterThanDebt() {
        //given
        Long loanId = 1L;
        double amount = 20.00;

        DebtBalanceDTO debtBalanceDTO =
                new DebtBalanceDTO();
        debtBalanceDTO.setBalance(10.00);

        //when
        when(restTemplate
                .getForEntity(
                        paymentProperties
                                .getDebtApiUrl() +"/1", DebtBalanceDTO.class))
                .thenReturn(new ResponseEntity<>(debtBalanceDTO, HttpStatus.OK),
                        new ResponseEntity<>(debtBalanceDTO, HttpStatus.OK));

        //then
        assertThatExceptionOfType(PaymentException.class)
                .isThrownBy(() -> paymentService
                        .registerPayment(loanId, amount))
                .withMessage("El monto del pago no puede " +
                        "ser superior a la deuda");
    }
}