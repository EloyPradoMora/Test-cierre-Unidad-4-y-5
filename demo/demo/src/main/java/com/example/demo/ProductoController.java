package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @PostMapping("/validar")
    public ResponseEntity<ValidacionResponse> validarProducto(@RequestBody ValidacionRequest request) {
        if (request == null || request.getCadena() == null || request.getCadena().trim().isEmpty()) {
            List<ErrorDetalle> errores = new ArrayList<>();
            errores.add(new ErrorDetalle("cadena", "La cadena de entrada no puede estar vacía"));
            return ResponseEntity.badRequest().body(new ValidacionResponse(false, errores));
        }

        String[] partes = request.getCadena().split(",");

        if (partes.length != 6) {
            List<ErrorDetalle> errores = new ArrayList<>();
            errores.add(new ErrorDetalle("cadena", "La cadena no contiene los 6 atributos requeridos"));
            return ResponseEntity.badRequest().body(new ValidacionResponse(false, errores));
        }

        String codigoInput = partes[0].trim();
        String nombreInput = partes[1].trim();
        String stockStr    = partes[2].trim();
        String precioStr   = partes[3].trim();
        String rutInput    = partes[4].trim();
        String mailInput   = partes[5].trim();

        List<ErrorDetalle> errores = new ArrayList<>();
        Producto producto = new Producto();

        if (!producto.giveName(nombreInput)) {
            errores.add(new ErrorDetalle("nombre", "Nombre inválido o supera los 30 caracteres"));
        }

        try {
            int stockInt = Integer.parseInt(stockStr);
            if (!producto.giveStock(stockInt)) {
                errores.add(new ErrorDetalle("stock", "El stock debe ser un entero mayor o igual a 0"));
            }
        } catch (NumberFormatException e) {
            errores.add(new ErrorDetalle("stock", "El formato del stock no es un número válido"));
        }

        try {
            int precioInt = Integer.parseInt(precioStr);
            if (!producto.givePrice(precioInt)) {
                errores.add(new ErrorDetalle("precio", "El precio debe ser un entero mayor o igual a 0"));
            }
        } catch (NumberFormatException e) {
            errores.add(new ErrorDetalle("precio", "El formato del precio no es un número válido"));
        }

        if (!producto.ingresarRUTProveedor(rutInput)) {
            errores.add(new ErrorDetalle("rut_proveedor", "RUT inválido o no encontrado en el listado de proveedores"));
        }

        if (!producto.insertProviderEmail(mailInput)) {
            errores.add(new ErrorDetalle("mail_provider", "Formato de correo no válido o no coincide con el proveedor"));
        }

        if (!errores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidacionResponse(false, errores));
        }

        return ResponseEntity.ok(new ValidacionResponse(true, "Producto validado correctamente"));
    }
}