package com.odos.smartaqua.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class Helper {

	public static boolean sendOTP(String mobilenumber, Long rndNumber, String akvalue, String skvalue, String utype,
			String senderValue, String message) {

		try {
			String token = AquaConstants.apikey + URLEncoder.encode(akvalue, AquaConstants.utf);
			String secret = AquaConstants.secret + URLEncoder.encode(skvalue, AquaConstants.utf);
			String usetype = AquaConstants.usetype + URLEncoder.encode(utype, AquaConstants.utf);
			String mobile = AquaConstants.phone + URLEncoder.encode(mobilenumber, AquaConstants.utf);
			String messageText = AquaConstants.message + URLEncoder.encode(rndNumber + message, AquaConstants.utf);
			String senderId = AquaConstants.senderid + URLEncoder.encode(senderValue, AquaConstants.utf);
			//URL obj = new URL(AquaConstants.url + token + secret + usetype + mobile + messageText + senderId);
			URL obj = new URL("https://www.smsstriker.com/API/sms.php?username=suryaa&password=Surya@7891"
					+ "&from=ODOSPL&to="+mobilenumber+"&msg="+URLEncoder.encode(rndNumber + message, AquaConstants.utf)
					+"&type=1&template_id=1707165837782002698");
			HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
			httpConnection.setDoOutput(true);
			BufferedReader bufferedReader = null;
			if (httpConnection.getResponseCode() == 200) {
				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
			} else {
				bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
			}

			StringBuilder content = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line).append("\n");
			}
			bufferedReader.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static long createRandomInteger(int aStart, long aEnd) {
		Random r = new Random();
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		long range = aEnd - (long) aStart + 1;
		long fraction = (long) (range * r.nextDouble());
		long randomNumber = fraction + (long) aStart;
		return randomNumber;

	}

}
