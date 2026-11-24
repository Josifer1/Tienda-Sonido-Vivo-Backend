package api_sonido_vivo.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import api_sonido_vivo.dto.UsuarioActualizarSolicitud;
import api_sonido_vivo.dto.UsuarioCrearSolicitud;
import api_sonido_vivo.entity.Rol;
import api_sonido_vivo.entity.Usuario;
import api_sonido_vivo.repository.UsuarioRepositorio;
import api_sonido_vivo.utilidades.ValidadorRun;

@Service
public class UsuarioServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder codificadorContrasena;

    public UsuarioServicio(UsuarioRepositorio usuarioRepositorio, PasswordEncoder codificadorContrasena) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.codificadorContrasena = codificadorContrasena;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepositorio.findAll();
    }

    public Usuario obtenerPorId(Long id) {
        return usuarioRepositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe un usuario con el id " + id));
    }

    public Usuario crear(UsuarioCrearSolicitud datos) {
        // Se normaliza antes de guardar y comparar: el RUN en mayúscula y el correo en minúscula
        String run = datos.run().trim().toUpperCase();
        String correo = datos.correo().trim().toLowerCase();

        if (!ValidadorRun.esValido(run)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El RUN ingresado no es válido (dígito verificador incorrecto)");
        }
        if (usuarioRepositorio.existsByRun(run)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con el RUN " + run);
        }
        if (usuarioRepositorio.existsByCorreo(correo)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con el correo " + correo);
        }

        Usuario usuario = new Usuario();
        usuario.setRun(run);
        usuario.setNombre(datos.nombre().trim());
        usuario.setApellidos(datos.apellidos().trim());
        usuario.setCorreo(correo);
        // La contraseña real nunca se guarda: solo su hash
        usuario.setContrasena(codificadorContrasena.encode(datos.contrasena()));
        usuario.setRol(datos.rol() != null ? datos.rol() : Rol.CLIENTE);
        usuario.setFechaNacimiento(datos.fechaNacimiento());
        usuario.setRegion(datos.region().trim());
        usuario.setComuna(datos.comuna().trim());
        usuario.setDireccion(datos.direccion().trim());

        return usuarioRepositorio.save(usuario);
    }

    public Usuario actualizar(Long id, UsuarioActualizarSolicitud datos) {
        Usuario existente = obtenerPorId(id);
        String correo = datos.correo().trim().toLowerCase();

        // El correo nuevo no puede pertenecer a OTRO usuario (al mismo sí, si no lo cambió)
        usuarioRepositorio.findByCorreo(correo).ifPresent(otro -> {
            if (!otro.getId().equals(id)) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "Ya existe un usuario con el correo " + correo);
            }
        });

        existente.setNombre(datos.nombre().trim());
        existente.setApellidos(datos.apellidos().trim());
        existente.setCorreo(correo);
        existente.setRol(datos.rol());
        existente.setFechaNacimiento(datos.fechaNacimiento());
        existente.setRegion(datos.region().trim());
        existente.setComuna(datos.comuna().trim());
        existente.setDireccion(datos.direccion().trim());

        return usuarioRepositorio.save(existente);
    }

    public void eliminar(Long id) {
        Usuario existente = obtenerPorId(id);
        usuarioRepositorio.delete(existente);
    }
}