package api_sonido_vivo.entity;

// Los tres perfiles del sistema, definidos desde la primera evaluación
public enum Rol {
    ADMINISTRADOR, // acceso total
    VENDEDOR,      // ve productos y órdenes
    CLIENTE        // solo la tienda
}