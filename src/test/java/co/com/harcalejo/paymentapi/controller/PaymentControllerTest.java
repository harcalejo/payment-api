package co.com.harcalejo.paymentapi.controller;

import co.com.harcalejo.paymentapi.dto.RegisterPaymentResponseDTO;
import co.com.harcalejo.paymentapi.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}