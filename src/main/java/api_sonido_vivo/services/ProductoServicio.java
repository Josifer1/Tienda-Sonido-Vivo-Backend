package api_sonido_vivo.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import api_sonido_vivo.entity.Producto;
import api_sonido_vivo.repository.ProductoRepositorio;

@Service
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    // Spring entrega el repositorio automáticamente al crear el servicio
    public ProductoServicio(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    public List<Producto> listarTodos() {
        return productoRepositorio.findAll();
    }

    public Producto obtenerPorCodigo(String codigo) {
        return productoRepositorio.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existe un producto con el código " + codigo));
    }

    public Producto crear(Producto producto) {
        if (productoRepositorio.existsById(producto.getCodigo())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Ya existe un producto con el código " + producto.getCodigo());
        }
        return productoRepositorio.save(producto);
    }

    public Producto actualizar(String codigo, Producto datosNuevos) {
        Producto existente = obtenerPorCodigo(codigo);

        existente.setNombre(datosNuevos.getNombre());
        existente.setMarca(datosNuevos.getMarca());
        existente.setModelo(datosNuevos.getModelo());
        existente.setDescripcion(datosNuevos.getDescripcion());
        existente.setPrecio(datosNuevos.getPrecio());
        existente.setStock(datosNuevos.getStock());
        existente.setStockCritico(datosNuevos.getStockCritico());
        existente.setCategoria(datosNuevos.getCategoria());
        existente.setImagen(datosNuevos.getImagen());

        return productoRepositorio.save(existente);
    }

    public void eliminar(String codigo) {
        Producto existente = obtenerPorCodigo(codigo);
        productoRepositorio.delete(existente);
    }
}