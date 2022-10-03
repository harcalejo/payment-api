package co.com.harcalejo.paymentapi.service;

import co.com.harcalejo.paymentapi.config.PaymentProperties;
import co.com.harcalejo.paymentapi.dto.DebtBalanceDTO;
import co.com.harcalejo.paymentapi.dto.RegisterPaymentResponseDTO;
import co.com.harcalejo.paymentapi.entity.Payment;
import co.com.harcalejo.paymentapi.exception.PaymentException;
import co.com.harcalejo.paymentapi.repository.PaymentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
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
     * Bean de configuración para el manejo de parametros de negocio
     */
    private final PaymentProperties paymentProperties;

    /**
     * Bean para el consumo de servicios rest
     */
    private final RestTemplate restTemplate;

    /**
     * Constructor con el que se implementa la inyeccion de dependencias
     *
     * @param paymentRepository dependencia del bean de persistencia
     * @param paymentProperties dependencia del bean de propiedades
     * @param restTemplate dependencia del bean para el consumo de Rest
     */
    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              PaymentProperties paymentProperties,
                              RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.paymentProperties = paymentProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public RegisterPaymentResponseDTO registerPayment(
            Long loanId, double amount) {

        final double currentDebt = getDebtLoan(loanId);

        if(amount > currentDebt) {
            throw new PaymentException(
                    "El monto del pago no puede ser " +
                            "superior a la deuda");
        }

        Payment payment = new Payment();
        payment.setRegisterDate(LocalDate.now());
        payment.setLoanId(loanId);
        payment.setAmount(amount);
        Payment paymentRegistred
                = paymentRepository.save(payment);

        final double newDebt = getDebtLoan(loanId);

        return new RegisterPaymentResponseDTO(
                paymentRegistred.getId(),
                loanId,
                newDebt);
    }

    /**
     * Metodo encargado de obtener el balance de la deuda del
     * API del dominio.
     *
     * @param loanId identificador del prestamo
     * @return el valor del balance actual del prestamo
     */
    private double getDebtLoan(Long loanId) {
        final ResponseEntity<DebtBalanceDTO> response =
                restTemplate.getForEntity(
                paymentProperties
                        .getDebtApiUrl() + "/" + loanId, DebtBalanceDTO.class);

        return response
                .getBody().getBalance();
    }

    @Override
    public List<Payment> getPaymentsLoan(Long loanId) {
        return paymentRepository.findByLoanId(loanId);
    }
}
