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
import org.springframework.web.server.ResponseStatusException;

import api_sonido_vivo.entity.Producto;
import api_sonido_vivo.services.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones CRUD del catálogo de productos")
public class ProductoControlador {

    private final ProductoServicio productoServicio;

    public ProductoControlador(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping
    @Operation(summary = "Listar todos los productos")
    public List<Producto> listar() {
        return productoServicio.listarTodos();
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Obtener un producto por su código")
    public Producto obtener(@PathVariable String codigo) {
        return productoServicio.obtenerPorCodigo(codigo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear un producto")
    public Producto crear(@Valid @RequestBody Producto producto) {
        return productoServicio.crear(producto);
    }

    @PutMapping("/{codigo}")
    @Operation(summary = "Actualizar un producto existente")
    public Producto actualizar(@PathVariable String codigo, @Valid @RequestBody Producto producto) {
        if (!codigo.equals(producto.getCodigo())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "El código de la URL y el del cuerpo no coinciden");
        }
        return productoServicio.actualizar(codigo, producto);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar un producto")
    public void eliminar(@PathVariable String codigo) {
        productoServicio.eliminar(codigo);
    }
}