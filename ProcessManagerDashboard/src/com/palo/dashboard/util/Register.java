package com.palo.dashboard.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Register {
	public static String registerSerials() {
		InputStream resource = Register.class.getClassLoader().getResourceAsStream("License");
		BufferedReader reader = new BufferedReader(new InputStreamReader(resource));
		try {
			String companyName = reader.readLine();
			reader.readLine();
			String license = reader.readLine();
			reader.close();
			resource.close();
			return license;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		
	}
}
