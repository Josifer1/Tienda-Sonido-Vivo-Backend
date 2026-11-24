package api_sonido_vivo.dto;

import java.time.LocalDate;

import api_sonido_vivo.entity.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Datos que llegan para crear un usuario. Se usa un DTO (objeto de transferencia de datos)
// y no la entidad, porque aquí la contraseña viene en texto y debe medir de 4 a 10 caracteres,
// mientras que en la entidad se guarda cifrada y mide 60.
public record UsuarioCrearSolicitud(

        @NotBlank(message = "El RUN es obligatorio")
        @Size(min = 7, max = 9, message = "El RUN debe tener entre 7 y 9 caracteres")
        String run,

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

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 4, max = 10, message = "La contraseña debe tener entre 4 y 10 caracteres")
        String contrasena,

        // Opcional: si no viene, el usuario se crea como CLIENTE
        Rol rol,

        // Opcional
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