package Herramientas;

import java.security.MessageDigest;

public class Encriptacion {

	public String encriptaDato(String tipo, String cadena) {
		try {
			byte[] digest = null;
			byte[] buffer = cadena.getBytes();

			MessageDigest messageDigest = MessageDigest.getInstance(tipo);
			messageDigest.reset();
			messageDigest.update(buffer);
			digest = messageDigest.digest();

			return toHexadecimal(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String toHexadecimal(byte[] digest) {
		String hash = "";
		for (byte aux : digest) {
			int b = aux & 0xff;
			if (Integer.toHexString(b).length() == 1)
				hash += "0";
			hash += Integer.toHexString(b);
		}
		return hash;
	}

}
