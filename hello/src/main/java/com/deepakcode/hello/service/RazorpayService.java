package com.deepakcode.hello.service;

import java.util.Base64;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

// Razorpay Service
@Service
public class RazorpayService {
  @Autowired RazorpayClient razorpayClient;
  @Autowired String razorpayKeySecret;

  public String createOrder(Double amount) throws RazorpayException {
    JSONObject orderRequest = new JSONObject();
    orderRequest.put("amount", (int)(amount * 100));
    orderRequest.put("currency", "INR");
    orderRequest.put("receipt", UUID.randomUUID().toString());
    Order order = razorpayClient.orders.create(orderRequest);
    return order.get("id");
  }

  public boolean verifyPayment(String orderId, String paymentId, String signature) {
    try {
      String payload = orderId + "|" + paymentId;
      String expectedSignature = hmacSha256(payload, razorpayKeySecret);
      return expectedSignature.equals(signature);
    } catch (Exception e) {
      return false;
    }
  }

  private String hmacSha256(String data, String key) throws Exception {
    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");
    sha256_HMAC.init(secretKey);
    byte[] hash = sha256_HMAC.doFinal(data.getBytes());
    return Base64.getEncoder().encodeToString(hash);
  }
}