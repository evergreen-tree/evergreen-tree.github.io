package com.palo.dashboard.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.palo.dashboard.po.ValidateResult;
import com.palo.dashboard.util.Register;
import com.palo.dashboard.util.ServerContext;

@Controller
@RequestMapping(value = "/validate")
public class Validator {
	String serials = ServerContext.getDefaultSerial();

	@RequestMapping(value = "/1")
	public void doValid(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException,
			IOException {
		String validateCode = Register.registerSerials();
		ValidateResult result = new ValidateResult();
		if (!isValid(serials, validateCode)) {
			result.setStatus("false");
			result.setSerials(serials);
		} else {
			result.setStatus("true");
			result.setSerials(serials);
		}
		String callBack = request.getParameter("callback");
		response.getOutputStream().write(new String(callBack + "(").getBytes());
		byte[] bytes = ("{\"serials\":\"" + result.getSerials() + "\", \"status\" : \"" + result.getStatus() + "\"}")
				.getBytes();
		response.getOutputStream().write(bytes);
		response.getOutputStream().write(new String(");").getBytes());
		response.setContentType("application/javascript");
	}

	@RequestMapping(value = "/2")
	public String test() {
		return "dashboard";
	}

	public boolean isValid(String serials, String validateCode) throws NoSuchAlgorithmException {
		serials = serials + "EFS";
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(serials.getBytes());
		byte b[] = md.digest();

		int i;

		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < b.length; offset++) {
			i = b[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		String serialStr = buf.toString();
		if (serialStr.equals(validateCode)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		new Validator().isValid("YFTAFFETCF", "");
	}
}
