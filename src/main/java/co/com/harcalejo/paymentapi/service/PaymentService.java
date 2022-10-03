package co.com.harcalejo.paymentapi.service;

import co.com.harcalejo.paymentapi.dto.RegisterPaymentResponseDTO;
import co.com.harcalejo.paymentapi.entity.Payment;

import java.util.List;

/**
 * La interface {@code PaymentService} es el componente encargado de definir
 * las capacidades del servicio de Pagos en este caso asociados a los
 * prestamos.
 *
 * @author Hugo Alejandro Rodriguez
 * @version 1.0.0
 */
public interface PaymentService {

    /**
     * Metodo para el registro de pagos asociados a un prestamo. Este
     * debe validar que el pago no sea superior al valor de la deuda
     * actual.
     *
     * @param loanId identificador unico del prestamo
     * @param amount monto del pago que se esta registrando
     * @return objeto que contiene informacion del pago registrado y
     *          el monto de la deuda
     */
    RegisterPaymentResponseDTO registerPayment(
            Long loanId, double amount);

    /**
     * Metodo que permite consultar todos los pagos realizados
     * a un prestamo
     *
     * @param loanId identificador unico del prestamo
     * @return Lista de los pagos asociados al prestamo
     */
    List<Payment> getPaymentsLoan(Long loanId);
}
