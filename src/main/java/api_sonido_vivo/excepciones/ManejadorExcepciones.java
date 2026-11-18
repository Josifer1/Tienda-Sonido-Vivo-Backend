package api_sonido_vivo.excepciones;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

// Captura los errores de todos los controladores y los convierte en una respuesta JSON clara
@RestControllerAdvice
public class ManejadorExcepciones {

    // Los datos enviados no cumplen las reglas de la entidad (@NotBlank, @Min, @Size...)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RespuestaError> manejarValidacion(MethodArgumentNotValidException excepcion) {
        Map<String, String> errores = new LinkedHashMap<>();
        for (FieldError error : excepcion.getBindingResult().getFieldErrors()) {
            // Si un campo falla por más de una regla, se conserva solo el primer mensaje
            errores.putIfAbsent(error.getField(), error.getDefaultMessage());
        }
        RespuestaError respuesta = new RespuestaError(400, "Hay datos inválidos en la solicitud", errores);
        return ResponseEntity.badRequest().body(respuesta);
    }

    // Errores que lanzamos nosotros: 404 no existe, 409 duplicado, 400 códigos distintos...
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<RespuestaError> manejarEstado(ResponseStatusException excepcion) {
        String mensaje = excepcion.getReason() != null ? excepcion.getReason() : "No se pudo procesar la solicitud";
        RespuestaError respuesta = new RespuestaError(excepcion.getStatusCode().value(), mensaje, Map.of());
        return ResponseEntity.status(excepcion.getStatusCode()).body(respuesta);
    }

    // El cuerpo no es un JSON válido, o un campo trae un tipo incorrecto (por ejemplo texto en el precio)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RespuestaError> manejarCuerpoIlegible(HttpMessageNotReadableException excepcion) {
        RespuestaError respuesta = new RespuestaError(
                400, "El cuerpo de la solicitud no es un JSON válido o tiene un tipo de dato incorrecto", Map.of());
        return ResponseEntity.badRequest().body(respuesta);
    }
}