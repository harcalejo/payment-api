package co.com.harcalejo.paymentapi.controller;

import co.com.harcalejo.paymentapi.dto.RegisterPaymentRequestDTO;
import co.com.harcalejo.paymentapi.dto.RegisterPaymentResponseDTO;
import co.com.harcalejo.paymentapi.entity.Payment;
import co.com.harcalejo.paymentapi.service.PaymentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    /**
     * Bean del servicio de pagos
     */
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/loan/{loanId}")
    public ResponseEntity<RegisterPaymentResponseDTO> registerPayment(
        @RequestBody RegisterPaymentRequestDTO registerPaymentRequestDTO,
        @PathVariable Long loanId) {

        return new ResponseEntity<>(paymentService
                .registerPayment(loanId,
                        registerPaymentRequestDTO.getAmount()),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/loan/{loanId}")
    public ResponseEntity<List<Payment>> getPaymentsLoan(
            @PathVariable Long loanId) {
        return new ResponseEntity<>(paymentService
                .getPaymentsLoan(loanId), HttpStatus.OK);
    }

    @GetMapping(value = "/loan/{loanId}")
    public ResponseEntity<List<Payment>> getPaymentsLoanRegisterDateBefore(
            @PathVariable Long loanId,
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}")
                @DateTimeFormat(iso =
                        DateTimeFormat.ISO.DATE) LocalDate before) {
        return new ResponseEntity<>(paymentService
                .getPaymentsLoanRegisterDateBefore(
                        loanId, before), HttpStatus.OK);
    }
}
