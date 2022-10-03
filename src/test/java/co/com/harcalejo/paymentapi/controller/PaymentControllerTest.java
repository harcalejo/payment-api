package co.com.harcalejo.paymentapi.controller;

import co.com.harcalejo.paymentapi.dto.RegisterPaymentResponseDTO;
import co.com.harcalejo.paymentapi.entity.Payment;
import co.com.harcalejo.paymentapi.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @BeforeEach
    void setup() {

    }

    @Test
    void shouldRegisterPaymentLoan() throws Exception {
        //given
        Long loanId = 1L;
        double amount = 10.00;

        final RegisterPaymentResponseDTO registerPaymentResponseDTO =
                new RegisterPaymentResponseDTO(1L, loanId, 20.00);

        String registerJson = "{\n" +
                "    \"amount\": 10.00\n" +
                "}";

        //when
        when(paymentService.registerPayment(loanId, amount))
                .thenReturn(registerPaymentResponseDTO);

        //then
        this.mockMvc
                .perform(post("/payments/loan/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerJson))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnPaymentsLoanBeforeNowByDefault() throws Exception {
        //given
        Long loanId = 1L;

        Payment payment_a = new Payment();
        payment_a.setId(1L);
        payment_a.setLoanId(1L);
        payment_a.setAmount(10.00);
        payment_a.setRegisterDate(
                LocalDate.now()
                        .minusMonths(2));

        Payment payment_b = new Payment();
        payment_b.setId(2L);
        payment_b.setLoanId(1L);
        payment_b.setAmount(10.00);
        payment_b.setRegisterDate(
                LocalDate.now()
                        .minusMonths(5));

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment_a);
        paymentList.add(payment_b);

        //when
        when(paymentService
                .getPaymentsLoanRegisterDateBefore(any(), any()))
                .thenReturn(paymentList);

        //then
        this.mockMvc
                .perform(get("/payments/loan/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].loanId").value(1));
    }
}