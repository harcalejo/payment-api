package co.com.harcalejo.paymentapi.dto;

import lombok.Data;

/**
 * La clase {@code RegisterPaymentResponseDTO} nos permite definir los atributos de la
 * entidad de Pago y Deuda que retornaremos en registro de un nuevo Pago.
 *
 * @author Hugo Alejandro Rodriguez
 * @version 1.0.0
 */
@Data
public class RegisterPaymentResponseDTO {

    /**
     * Identificador unico del pago
     */
    private Long id;

    /**
     * Identificador unico del prestamo
     */
    private Long loanId;

    /**
     * Monto de la deuda
     */
    private double debt;
}
