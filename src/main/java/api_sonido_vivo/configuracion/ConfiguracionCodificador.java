package api_sonido_vivo.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ConfiguracionCodificador {

    // BCrypt transforma la contraseña en un hash irreversible. Con la misma contraseña
    // produce un hash distinto cada vez, y solo sabe comprobar si una contraseña coincide.
    @Bean
    public PasswordEncoder codificadorContrasena() {
        return new BCryptPasswordEncoder();
    }
}