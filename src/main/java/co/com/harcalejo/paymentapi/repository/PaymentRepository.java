package co.com.harcalejo.paymentapi.repository;

import co.com.harcalejo.paymentapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * La interface {@code PaymentRepository} es el componente encargado del acceso a los
 * datos de nuestra API. Permitiendonos por ejemplo guardar, modificar, eliminar y
 * consultar registros de un Payment (Ver clase {@link Payment}).
 *
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Metodo que consulta los pagos registrador por id de prestamo
     *
     * @param loanId identificador del prestamo
     * @return listado de pagos registrados
     */
    List<Payment> findByLoanId(Long loanId);

    /**
     * Metodo que permite consultar los pagos de un prestamo, ademas
     * incluye la fecha de registro para identificar los pagos
     * realizados antes de esta fecha dada.
     *
     * @param loanId identificador del prestamo
     * @param registerDate representa la fecha de creacion de pago
     *                     maxima para obtener los registros antes
     *                     de esta.
     * @return lista de pagos que corresponden a la consulta
     */
    List<Payment> findByLoanIdAndRegisterDateBefore(
            Long loanId, LocalDate registerDate);
}
