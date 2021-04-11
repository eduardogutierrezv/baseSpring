package com.example.springback.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorRequest {

    public static boolean validaRut(String rutAndDv, boolean tienePuntos) {

        String centena = "[1-9][0-9][0-9]|[1-9][0-9]|[1-9]";
        String milesima = "[1-9][0-9][0-9]\\.[0-9][0-9][0-9]|[1-9][0-9]\\.[0-9][0-9][0-9]|[1-9]\\.[0-9][0-9][0-9]" + "|"
                + centena;
        String millonesima = "[1-9][0-9][0-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9]|[1-9][0-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9]|[1-9]\\.[0-9][0-9][0-9]\\.[0-9][0-9][0-9]"
                + "|" + milesima;
        Pattern patternConPunto = Pattern.compile("^(" + millonesima + ")[-][0-9K]{1}$");// Con Puntos y con Guion

        Pattern patternSinPunto = Pattern.compile(
                "^([1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]|[1-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]|[1-9][0-9][0-9][0-9][0-9][0-9][0-9]|[1-9][0-9][0-9][0-9][0-9][0-9]|[1-9][0-9][0-9][0-9][0-9]|[1-9][0-9][0-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9]|[1-9])[-][0-9K]{1}$");

        Matcher matcher = null;

        if (tienePuntos) {
            matcher = patternConPunto.matcher(rutAndDv);
        } else {
            matcher = patternSinPunto.matcher(rutAndDv);
        }

        if (matcher.matches() == false) {
            return false;
        } else {
            char dv = rutAndDv.split("-")[1].charAt(0);
            Integer rut = Integer.parseInt(rutAndDv.split("-")[0].replace(".", ""));
            return validarRut(rut, dv);
        }
    }

    /*
     * Método Estático que valida si un rut es válido Fuente :
     * http://www.creations.cl/2009/01/generador-de-rut-y-validador/
     */
    public static boolean validarRut(int rut, char dv) {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 75);
    }

}
