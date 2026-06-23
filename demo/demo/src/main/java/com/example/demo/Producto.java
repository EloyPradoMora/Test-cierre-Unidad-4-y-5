package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Producto {
    private String code;
    private String name;
    private int stock;
    private int price;
    private String providerRUT;
    private String providerEmail;

    public Producto() {
    }

    boolean giveName(Object name) {
        if (name instanceof String && name.toString().length() <= 30) {
            this.name = (String) name;
            return true;
        }
        return false;
    }

    void createCode(LocalDateTime timeOfCreation) {
        String codigoCorrecto = String.valueOf(name.charAt(0)).toUpperCase();
        String fechaHoraRaw = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(timeOfCreation);
        System.out.println(fechaHoraRaw);
        String refinedTime = fechaHoraRaw.split(" ")[0].split("/")[0];
        refinedTime += fechaHoraRaw.split(" ")[0].split("/")[1];
        refinedTime += fechaHoraRaw.split(" ")[0].split("/")[2].substring(2, 4);

        refinedTime += fechaHoraRaw.split(" ")[1].split(":")[0];
        refinedTime += fechaHoraRaw.split(" ")[1].split(":")[1];
        codigoCorrecto += refinedTime;
        this.code = codigoCorrecto;
    }

    boolean giveStock(Object stock) {
        if (stock instanceof Integer && (Integer) stock >= 0) {
            this.stock = (Integer) stock;
            return true;
        }
        return false;
    }

    boolean givePrice(Object price) {
        if (price instanceof Integer && (Integer) price >= 0) {
            this.price = (Integer) price;
            return true;
        }
        return false;
    }

    boolean ingresarRUTProveedor(Object rut) {
        if (rut instanceof String && ((String) rut).split("-").length == 2) {
            String primeraParteDelRUT = ((String) rut).split("-")[0].replace(".", "");
            String digitoVerificador = ((String) rut).split("-")[1];

            primeraParteDelRUT = new StringBuilder(primeraParteDelRUT).reverse().toString();

            int descuento = 0;
            int suma = 0;
            try {
                for (int i = 0; i < primeraParteDelRUT.length(); i++) {
                    suma += Integer.parseInt(String.valueOf(primeraParteDelRUT.charAt(i))) * (i + 2 - descuento);

                    if ((i + 2) % 7 == 0) {
                        descuento += 6;
                    }
                }

                int digitoObtenido = 11 - (suma % 11);
                String digitoObtenidoString;
                if (digitoObtenido == 11) {
                    digitoObtenidoString = "0";
                } else if (digitoObtenido == 10) {
                    digitoObtenidoString = "K";
                } else {
                    digitoObtenidoString = String.valueOf(digitoObtenido);
                }

                if (digitoObtenidoString.equalsIgnoreCase(digitoVerificador)) {
                    return rutInCSV(rut.toString());
                }
            } catch (Exception e) {
                return false;

            }
        }
        return false;
    }

    boolean rutInCSV(String rut) {
        String csvURL = "proveedores.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvURL))) {
            while ((line = br.readLine()) != null) {
                String RUTFromCSV = line.split(";")[0].trim();
                if (RUTFromCSV.equals(rut)) {
                    this.providerRUT = rut;
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }

    boolean insertProviderEmail(Object providerEmail) {
        if (providerEmail instanceof String) {
            String csvURL = "proveedores.csv";
            String line;
            try (BufferedReader br = new BufferedReader(new FileReader(csvURL))) {
                while ((line = br.readLine()) != null) {
                    String emailFromCSV = line.split(";")[2].trim();
                    if (emailFromCSV.equals(providerEmail)) {
                        this.providerEmail = providerEmail.toString();
                        return true;
                    }
                }
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }


    public String getCode() {
        return code;
    }
}