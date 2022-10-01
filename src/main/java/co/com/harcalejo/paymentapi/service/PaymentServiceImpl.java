package co.com.harcalejo.paymentapi.service;

import co.com.harcalejo.paymentapi.dto.RegisterPaymentResponseDTO;
import co.com.harcalejo.paymentapi.entity.Payment;
import co.com.harcalejo.paymentapi.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementacion del servicio de pagos {@link PaymentService}
 */
@Service
public class PaymentServiceImpl implements PaymentService{

    /**
     * Bean del objeto de persistencia para la interaccion con la
     * base de datos de pagos
     */
   private final PaymentRepository paymentRepository;

    /**
     * Constructor con el que se implementa la inyeccion de dependencias
     *
     * @param paymentRepository dependencia del bean de persistencia
     */
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public RegisterPaymentResponseDTO registerPayment(
            Long loanId, double amount) {
        return null;
    }

    @Override
    public List<Payment> getPaymentsLoan(Long loanId) {
        return paymentRepository.findByLoanId(loanId);
    }
}
