package br.com.mattioli.cursoms.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {
	
	public static List<Integer> decodeIntList(String s){
		
		String[] vet = s.split(",");
		
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
			
		}
		return list;
	
	}
	
	public static String decodeParam(String g) {
		try {
			return URLDecoder.decode(g, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			return "";
		}
		
	}

}
