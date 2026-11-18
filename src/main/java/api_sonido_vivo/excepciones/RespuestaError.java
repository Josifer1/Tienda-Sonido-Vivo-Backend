package api_sonido_vivo.excepciones;

import java.util.Map;

// Forma única en que la API informa cualquier error, para que el frontend siempre sepa leerla.
// "errores" trae el detalle por campo (por ejemplo: precio -> "El precio no puede ser negativo").
public record RespuestaError(int estado, String mensaje, Map<String, String> errores) {
}