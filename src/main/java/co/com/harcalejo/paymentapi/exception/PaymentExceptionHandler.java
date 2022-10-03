package co.com.harcalejo.paymentapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class PaymentExceptionHandler {

    @ExceptionHandler(value = PaymentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handlePaymentException(
            PaymentException paymentException) {
        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                LocalDate.now(),
                paymentException.getMessage());
    }

}
