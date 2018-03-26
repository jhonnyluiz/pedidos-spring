package com.jlcabral.pedidos.architecture;

/**
 * @author Jhonny Cabral
 * @date 22 de mar de 2018
 */
public class UtilObject {

	public static boolean isVazio(String obj) {
		if (isNull(obj) || obj.equals(Constante.VAZIO) ) {
			return true;
		}
		return false;
	}
	
	public static boolean isPreenchido(String obj) {
		return !isVazio(obj);
	}
	
	public static boolean isNull(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}
}
