package co.com.harcalejo.paymentapi.controller;

import co.com.harcalejo.paymentapi.entity.Payment;
import co.com.harcalejo.paymentapi.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/loan/{loanId}")
    public ResponseEntity<List<Payment>> getPaymentsLoan(
            @PathVariable Long loanId) {
        return new ResponseEntity<>(paymentService
                .getPaymentsLoan(loanId), HttpStatus.OK);
    }
}
