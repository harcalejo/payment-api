package co.com.harcalejo.paymentapi.repository;

import co.com.harcalejo.paymentapi.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

}
