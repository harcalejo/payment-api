package co.com.harcalejo.paymentapi.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Bean de configuracion usado para el mapeo de constantes y carga de
 * properties asociadas a las reglas y comportamientos segun el negocio
 * del dominio de Pagos
 *
 * @author Hugo Alejandro Rodriguez
 * @version 1.0.0
 */
@Configuration
@Getter
public class PaymentProperties {

    @Value("${api.client.debt.url}")
    private String debtApiUrl;
}
