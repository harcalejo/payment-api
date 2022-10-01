package co.com.harcalejo.paymentapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * La clase {@code Payment} nos permite representar la entidad de negocio de Pagos
 * como un objeto en nuestra API. Esta entidad de negocio contiene la información
 * de un Pago.
 *
 * @author Hugo Alejandro Rodriguez
 * @version 1.0.0
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Payment {

    /**
     * Identificador unico generado por secuencia de base de datos
     */
    @Id
    @GeneratedValue(strategy =
            GenerationType.SEQUENCE)
    private Long id;

    /**
     * Identificador del prestamo asociado al pago
     */
    private Long loanId;

    /**
     * Fecha en la que se registro el pago
     */
    private LocalDate registerDate;
}
