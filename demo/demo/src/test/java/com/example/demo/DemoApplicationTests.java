package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {
	Producto producto = new Producto();

	@Test
	void contextLoads() {
	}

	@Test
	void testCodigo(){
		LocalDateTime fechaFija = LocalDateTime.of(2026, 10, 15, 14, 30, 0);
		producto.giveName("computador");
		producto.createCode(fechaFija);
		String codigoGenerado = producto.getCode();
		String codigoEsperado = "C1510261430";
		assertEquals(codigoEsperado, codigoGenerado);
	}

	@Test
	void testNombreValido(){
		Assertions.assertTrue(producto.giveName("Jabon"));
		Assertions.assertTrue(producto.giveName("QWERTYUIOASDFGHJKZXCVBNMQWERTY"));
	}

	@Test
	void testNombreInvalido(){
		Assertions.assertFalse(producto.giveName("QWERTYUIOASDFGHJKZXCVBNMQWERTYA"));
		Assertions.assertFalse(producto.giveName(123456));
		Assertions.assertFalse(producto.giveName(1.3456));
		Assertions.assertFalse(producto.giveName(true));
	}

	@Test
	void testStock(){
		Assertions.assertTrue(producto.giveStock(0));
		Assertions.assertTrue(producto.giveStock(1));
		Assertions.assertTrue(producto.giveStock(150000));
	}

	@Test
	void testStockInvalido(){
		Assertions.assertFalse(producto.giveStock(-5));
		Assertions.assertFalse(producto.giveStock(-1));
		Assertions.assertFalse(producto.giveStock("hola"));
		Assertions.assertFalse(producto.giveStock(true));
	}

	@Test
	void testPrecio(){
		Assertions.assertTrue(producto.givePrice(0));
		Assertions.assertTrue(producto.givePrice(1));
		Assertions.assertTrue(producto.givePrice(150000));
	}

	@Test
	void testPrecioInvalido(){
		Assertions.assertFalse(producto.givePrice(-5));
		Assertions.assertFalse(producto.givePrice(-1));
		Assertions.assertFalse(producto.givePrice("hola"));
		Assertions.assertFalse(producto.givePrice(true));
	}

	@Test
	void testRutValido(){
		Assertions.assertTrue(producto.ingresarRUTProveedor("79527050-7"));
		Assertions.assertTrue(producto.ingresarRUTProveedor("96772810-1"));
		Assertions.assertTrue(producto.ingresarRUTProveedor("86381300-K"));
	}

	@Test
	void testRutInvalido(){
		Assertions.assertFalse(producto.ingresarRUTProveedor("20.645.322-3"));
		Assertions.assertFalse(producto.ingresarRUTProveedor("20645322-3"));
		Assertions.assertFalse(producto.ingresarRUTProveedor("20.645.3223"));
		Assertions.assertFalse(producto.ingresarRUTProveedor("206453223"));
		Assertions.assertFalse(producto.ingresarRUTProveedor(206453223));
		Assertions.assertFalse(producto.ingresarRUTProveedor(20645322.3));
		Assertions.assertFalse(producto.ingresarRUTProveedor(producto));
	}

	@Test
	void testMails(){
		Assertions.assertTrue(producto.insertProviderEmail("gerencia@surlat.cl"));
		Assertions.assertTrue(producto.insertProviderEmail("gerencia@vinos.cl"));
		Assertions.assertTrue(producto.insertProviderEmail("admin@frutam.cl"));
	}

	@Test
	void testMailsInvalido(){
		Assertions.assertFalse(producto.insertProviderEmail("gerencia@surlatcl"));
		Assertions.assertFalse(producto.insertProviderEmail("gerenciasurlatcl"));
		Assertions.assertFalse(producto.insertProviderEmail("gerenciasurlat.cl"));
		Assertions.assertFalse(producto.insertProviderEmail("e.prado02@ufromail.cl"));
	}
}