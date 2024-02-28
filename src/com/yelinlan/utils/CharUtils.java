package com.yelinlan.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *@项目名称: dataStructure
 *@类名称: CharUtils
 *@类描述:
 *@创建人: yll
 *@创建时间: 2024/2/24 20:23
 **/
public class CharUtils {

	public static final Map<Integer, Character> supMap = new HashMap<>();
	public static final Map<Integer, Character> subMap = new HashMap<>();
	public static final Map<Character, Character> matchMap = new HashMap<>();

	static {
		char[] sup = "⁰¹²³⁴⁵⁶⁷⁸⁹".toCharArray();
		for (int i = 0; i < sup.length; i++) {
			CharUtils.supMap.put(i, sup[i]);
		}
		char[] sub = "₀₁₂₃₄₅₆₇₈₉".toCharArray();
		for (int i = 0; i < sub.length; i++) {
			CharUtils.subMap.put(i, sub[i]);
		}

		matchMap.put('(', ')');
		matchMap.put('{', '}');
		matchMap.put('[', ']');
	}

	public static Boolean match(Character a, Character b){
		return b.equals(matchMap.get(a));
	}

	public static String sup(int num) {
		return convert(num, supMap);
	}

	public static String sub(int num) {
		return convert(num, subMap);
	}

	private static String convert(int num, Map<Integer, Character> subMap) {
		String numStr = String.valueOf(num);
		StringBuilder elem = new StringBuilder();
		for (int i = 0; i < numStr.length(); i++) {
			elem.append(subMap.get(numStr.charAt(i) - '0'));
		}
		return elem.toString();
	}

	public static String sub(double num) {
		String numStr = String.valueOf(num);
		StringBuilder sub = new StringBuilder();
		for (int i = 0; i < numStr.length(); i++) {
			Character character = subMap.get(numStr.charAt(i) - '0');
			if (character == null) {
				sub.append(numStr.charAt(i));
			} else {
				sub.append(character);
			}
		}
		if (num > 0) {
			sub.insert(0, '+');
		}
		return sub.toString();
	}

}