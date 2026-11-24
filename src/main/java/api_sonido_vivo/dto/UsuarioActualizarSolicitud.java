package api_sonido_vivo.dto;

import java.time.LocalDate;

import api_sonido_vivo.entity.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Datos que llegan para modificar un usuario. No trae el RUN (no se puede cambiar)
// ni la contraseña (cambiarla sería un servicio aparte).
public record UsuarioActualizarSolicitud(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
        String nombre,

        @NotBlank(message = "Los apellidos son obligatorios")
        @Size(max = 100, message = "Los apellidos no pueden superar los 100 caracteres")
        String apellidos,

        @NotBlank(message = "El correo es obligatorio")
        @Size(max = 100, message = "El correo no puede superar los 100 caracteres")
        @Pattern(regexp = "^[^\\s@]+@(duoc\\.cl|profesor\\.duoc\\.cl|gmail\\.com)$",
                flags = Pattern.Flag.CASE_INSENSITIVE,
                message = "Solo se aceptan correos @duoc.cl, @profesor.duoc.cl o @gmail.com")
        String correo,

        @NotNull(message = "El rol es obligatorio")
        Rol rol,

        LocalDate fechaNacimiento,

        @NotBlank(message = "La región es obligatoria")
        @Size(max = 60, message = "La región no puede superar los 60 caracteres")
        String region,

        @NotBlank(message = "La comuna es obligatoria")
        @Size(max = 60, message = "La comuna no puede superar los 60 caracteres")
        String comuna,

        @NotBlank(message = "La dirección es obligatoria")
        @Size(max = 300, message = "La dirección no puede superar los 300 caracteres")
        String direccion
) {
}