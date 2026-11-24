package api_sonido_vivo.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// CORS: el navegador bloquea las llamadas entre orígenes distintos (la web en el puerto 5173 y
// la API en el 8080) a menos que la API diga expresamente que ese origen está permitido.
@Configuration
public class ConfiguracionCors implements WebMvcConfigurer {

    private final String origenPermitido;

    // Si no existe la propiedad app.cors.origen-permitido se usa el servidor de desarrollo de Vite
    public ConfiguracionCors(@Value("${app.cors.origen-permitido:http://localhost:5173}") String origenPermitido) {
        this.origenPermitido = origenPermitido;
    }

    @Override
    public void addCorsMappings(CorsRegistry registro) {
        registro.addMapping("/api/**")
                .allowedOrigins(origenPermitido)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}