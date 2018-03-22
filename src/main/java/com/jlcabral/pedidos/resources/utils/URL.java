package com.jlcabral.pedidos.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import com.jlcabral.pedidos.architecture.Constante;
import com.jlcabral.pedidos.architecture.UtilObject;

/**
 * @author Jhonny Cabral
 * @date 22 de mar de 2018
 */
public class URL {

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, Constante.UTF8);
		} catch (UnsupportedEncodingException e) {
			return Constante.VAZIO;
		}
	}

	public static List<Long> decodeIntList(String s) {
		List<Long> list = new ArrayList<>();
		if (UtilObject.isPreenchido(s)) {
			for (String str : s.split(Constante.VIRGULA)) {
				list.add(new Long(str));
			}
		}
		return list;
	}
}
