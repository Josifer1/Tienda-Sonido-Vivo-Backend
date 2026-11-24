package api_sonido_vivo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import api_sonido_vivo.dto.UsuarioActualizarSolicitud;
import api_sonido_vivo.dto.UsuarioCrearSolicitud;
import api_sonido_vivo.dto.UsuarioRespuesta;
import api_sonido_vivo.services.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD de usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    public UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping
    @Operation(summary = "Listar todos los usuarios")
    public List<UsuarioRespuesta> listar() {
        return usuarioServicio.listarTodos().stream().map(UsuarioRespuesta::desde).toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por su id")
    public UsuarioRespuesta obtener(@PathVariable Long id) {
        return UsuarioRespuesta.desde(usuarioServicio.obtenerPorId(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un usuario")
    public UsuarioRespuesta crear(@Valid @RequestBody UsuarioCrearSolicitud datos) {
        return UsuarioRespuesta.desde(usuarioServicio.crear(datos));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar los datos de un usuario")
    public UsuarioRespuesta actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioActualizarSolicitud datos) {
        return UsuarioRespuesta.desde(usuarioServicio.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un usuario")
    public void eliminar(@PathVariable Long id) {
        usuarioServicio.eliminar(id);
    }
}