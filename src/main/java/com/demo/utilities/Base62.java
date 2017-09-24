package com.demo.utilities;

// copied and modified to work for long from: https://gist.github.com/jdcrensh/4670128
	
public class Base62 {

	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static final long BASE = ALPHABET.length();

	private Base62() {}

	public static String fromLong(long i) {
		StringBuilder sb = new StringBuilder("");
		if (i == 0) {
			return "a";
		}
		while (i > 0) {
			i = fromLong(i, sb);
		}
		return sb.reverse().toString();
	}

	private static long fromLong(long i, final StringBuilder sb) {
		long rem = i % BASE;
		sb.append(ALPHABET.charAt((int) rem));
		return i / BASE;
	}

	/*
	public static int toBase10(String str) {
		return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
	}

	private static int toBase10(char[] chars) {
		int n = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			n += toBase10(ALPHABET.indexOf(chars[i]), i);
		}
		return n;
	}

	private static int toBase10(int n, int pow) {
		return n * (int) Math.pow(BASE, pow);
	}
	*/
}
