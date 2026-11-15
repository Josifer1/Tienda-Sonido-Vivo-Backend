package api_sonido_vivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api_sonido_vivo.entity.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, String> {
}