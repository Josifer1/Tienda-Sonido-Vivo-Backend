package api_sonido_vivo.dto;

import java.time.LocalDate;

import api_sonido_vivo.entity.Rol;
import api_sonido_vivo.entity.Usuario;

// Lo que la API devuelve de un usuario. Fíjate que NO incluye la contraseña, ni siquiera cifrada.
public record UsuarioRespuesta(
        Long id,
        String run,
        String nombre,
        String apellidos,
        String correo,
        Rol rol,
        LocalDate fechaNacimiento,
        String region,
        String comuna,
        String direccion
) {

    // Convierte la entidad de la base de datos en la respuesta que sale hacia afuera
    public static UsuarioRespuesta desde(Usuario usuario) {
        return new UsuarioRespuesta(
                usuario.getId(),
                usuario.getRun(),
                usuario.getNombre(),
                usuario.getApellidos(),
                usuario.getCorreo(),
                usuario.getRol(),
                usuario.getFechaNacimiento(),
                usuario.getRegion(),
                usuario.getComuna(),
                usuario.getDireccion());
    }
}