package com.deepakcode.hello.Configuration;

import org.springframework.beans.factory.annotation.Value; // ✅ Correct import
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Configuration
public class RazorpayConfig {

  @Value("${razorpay.key_id}")
  private String keyId;

  @Value("${razorpay.key_secret}")
  private String keySecret;

  @Bean
  public RazorpayClient razorpayClient() throws RazorpayException {
    return new RazorpayClient(keyId, keySecret);
  }

  @Bean
  public String razorpayKeySecret() {
    return keySecret;
  }
}
