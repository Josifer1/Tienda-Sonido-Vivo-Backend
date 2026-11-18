package api_sonido_vivo.configuracion;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import api_sonido_vivo.entity.Producto;
import api_sonido_vivo.repository.ProductoRepositorio;


// Se ejecuta una vez, cada vez que la aplicación inicia.
// Si la tabla de productos está vacía, la llena con el catálogo de ejemplo.
@Component
public class CargadorDatosIniciales implements CommandLineRunner {

    private final ProductoRepositorio productoRepositorio;

    public CargadorDatosIniciales(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public void run(String... args) {
        // Si ya hay productos no se hace nada, para no duplicar datos en cada inicio
        if (productoRepositorio.count() > 0) {
            return;
        }

        productoRepositorio.saveAll(List.of(
            crearProducto("GA001", "Guitarra Acústica Folk", "Yamaha", "F310", "Guitarras Acústicas",
                129990, 8, 2, "Tapa de abeto, aros y fondo de meranti. Ideal para principiantes.",
                "/img/productos/ga001.jpg"),
            crearProducto("GE001", "Guitarra Eléctrica Stratocaster", "Squier", "Affinity Strat", "Guitarras Eléctricas",
                249990, 5, 2, "Cuerpo de álamo, mástil de arce, pastillas SSS.",
                "/img/productos/ge001.jpg"),
            crearProducto("BA001", "Bajo Eléctrico 4 Cuerdas", "Squier", "Affinity PJ", "Bajos Eléctricos",
                299990, 5, 2, "Pickup PJ, cuerpo álamo, mástil arce.",
                "/img/productos/ba001.jpg"),
            crearProducto("BT003", "Caja Snare 14\"", "Pearl", "STE1450", "Baterías",
                89990, 4, 2, "Acero, 14x5 pulgadas, 10 tensores.",
                "/img/productos/bt003.jpg"),
            crearProducto("TC001", "Teclado Digital 61 teclas", "Yamaha", "PSR-E373", "Teclados y Pianos",
                249990, 4, 2, "61 teclas sensibles al tacto, 622 voces.",
                "/img/productos/tc001.jpg"),
            crearProducto("AM002", "Amplificador Guitarra 40W", "Marshall", "MG40GFX", "Amplificadores",
                299990, 3, 2, "40W, 4 canales, efectos digitales integrados.",
                "/img/productos/am002.jpg"),
            crearProducto("MI001", "Micrófono Dinámico Cardioide", "Shure", "SM58", "Micrófonos",
                149990, 8, 2, "Estándar de la industria para voz en vivo.",
                "/img/productos/mi001.jpg"),
            crearProducto("PE001", "Pedal Distorsión", "Boss", "DS-1", "Pedales de Efectos",
                79990, 7, 2, "Clásico pedal de distorsión, 3 controles.",
                "/img/productos/pe001.jpg"),
            crearProducto("AC005", "Capotraste Guitarra", "Dunlop", "Trigger", "Accesorios",
                12990, 15, 5, "Capotraste de resorte, compatible con 6 cuerdas.",
                "/img/productos/ac005.jpg"),
            crearProducto("ES001", "Interfaz de Audio 2x2 USB", "Focusrite", "Scarlett Solo", "Estudio y Grabación",
                149990, 4, 2, "1 entrada XLR+instrumento, 2 salidas, 24bit/192kHz.",
                "/img/productos/es001.jpg")
        ));
    }

    // Método auxiliar para no repetir diez veces los mismos "set"
    private Producto crearProducto(String codigo, String nombre, String marca, String modelo,
            String categoria, int precio, int stock, int stockCritico, String descripcion, String imagen) {
        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setMarca(marca);
        producto.setModelo(modelo);
        producto.setCategoria(categoria);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setStockCritico(stockCritico);
        producto.setDescripcion(descripcion);
        producto.setImagen(imagen);
        return producto;
    }
}