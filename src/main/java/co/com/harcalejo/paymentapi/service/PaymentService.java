package co.com.harcalejo.paymentapi.service;

/**
 * La interface {@code PaymentService} es el componente encargado de definir
 * las capacidades del servicio de Pagos en este caso asociados a los
 * prestamos.
 *
 * @author Hugo Alejandro Rodriguez
 * @version 1.0.0
 */
public interface PaymentService {


    void registerPayment(Long loanId, double amount);
}
