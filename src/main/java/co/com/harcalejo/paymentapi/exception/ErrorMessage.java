package co.com.harcalejo.paymentapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class ErrorMessage {
    private int statusCode;
    private LocalDate date;
    private String message;
}
