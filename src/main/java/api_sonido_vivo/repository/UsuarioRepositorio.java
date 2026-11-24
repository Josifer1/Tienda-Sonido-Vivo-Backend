package api_sonido_vivo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api_sonido_vivo.entity.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    // Spring crea estas consultas solo con leer el nombre del método:
    // "existsByRun" significa "¿existe algún usuario con este run?"
    boolean existsByRun(String run);

    boolean existsByCorreo(String correo);

    Optional<Usuario> findByCorreo(String correo);
}