package com.deepakcode.hello.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVerificationDTO {
  @NotNull
  private String razorpayPaymentId;

  @NotNull
  private String razorpayOrderId;

  @NotNull
  private String razorpaySignature;

  // getters and setters
}
