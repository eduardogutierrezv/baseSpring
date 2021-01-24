package com.example.springback.util.utils;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.MDC;

public class Utils {

	public static String getValue(String key) {
		return MessageProperties.getInstance().getValue(key);
	}

	@Deprecated
	public static String getValue(String key, String traceCode) {
		return MessageProperties.getInstance().getValue(key, traceCode);
	}

	@Deprecated
	public static String genTraceCode(String appName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return appName + "-" + sdf.format(new Date());
	}

	public static void genTraceCode() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String traceCode = sdf.format(new Date());
		Random random = new Random();
		int randomValue = random.nextInt(1000001);
		MDC.put("traceCode", traceCode + "-" + randomValue);
	}

	public static String getTraceCode() {
		return MDC.get("traceCode");
	}

	public static String encrypt(String text, String key, String iv) {
		try {
			if (key.length() > 16) {
				new Exception("La llave de encriptación no puede exceder los 16 bytes.");
			}
			if (iv.length() > 16) {
				new Exception("El vector de inicialización no puede exceder los 16 bytes.");
			}
			if (key.length() < 16) {
				key = String.format("%1$-" + 16 + "s", key);
			}
			if (iv.length() < 16) {
				iv = String.format("%1$-" + 16 + "s", iv);
			}
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("UTF-8"), "AES"),
					new IvParameterSpec(iv.getBytes("UTF-8")));
			byte[] cipherText = cipher.doFinal(text.getBytes("UTF-8"));
			String cipherTextBase64 = Base64.getEncoder().encodeToString(cipherText);
			return cipherTextBase64;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

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

	public static String getRandomAlphaNumeric(int lenght, String AlphaNumericString) {
		StringBuilder sb = new StringBuilder(lenght);

		for (int i = 0; i < lenght; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public static Boolean esJuridica(String rut) throws Exception {
		rut = rut.trim();
		rut = rut.replace(".", "");
		rut = rut.replace("-", "");
		rut = rut.substring(0, rut.length() - 1);

		if (new Integer(rut) > 50000000) {
			return true;
		} else {
			return false;
		}
	}

}
