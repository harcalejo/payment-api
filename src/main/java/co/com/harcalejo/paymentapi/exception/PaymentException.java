package co.com.harcalejo.paymentapi.exception;

/**
 * La clase {@code PaymentException} es el componente encagado de las
 * excepciones customizadas relacionadas con el Pago.
 *
 * extends {@link RuntimeException}
 *
 * @author Hugo Alejandro Rodriguez
 * @version 1.0.0
 */
public class PaymentException extends RuntimeException{

    public PaymentException(String message) {
        super(message);
    }
}
