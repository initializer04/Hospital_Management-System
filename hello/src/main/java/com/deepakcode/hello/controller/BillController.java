package com.deepakcode.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepakcode.hello.dto.BillRequestDTO;
import com.deepakcode.hello.dto.PaymentVerificationDTO;
import com.deepakcode.hello.entity.Bill;
import com.deepakcode.hello.service.BillService;

import jakarta.validation.Valid;

// Controller
@RestController
@RequestMapping("/api/bills")
public class BillController {
  @Autowired private BillService billService;

  @PostMapping("/create")
  public ResponseEntity<Bill> create(@Valid @RequestBody BillRequestDTO request) {
    Bill bill = billService.generateBill(request.getPatientId(), request.getItems());
    return ResponseEntity.ok(bill);
  }

  @PostMapping("/verify")
  public ResponseEntity<String> verify(@RequestBody PaymentVerificationDTO dto) {
    boolean isVerified = billService.verifyAndMarkPaid(dto);
    return isVerified ? ResponseEntity.ok("Payment verified and bill marked as PAID.")
                      : ResponseEntity.status(400).body("Invalid payment.");
  }
}

// Sample JSON Request (POST /api/bills/verify)
/*
{
  "razorpayPaymentId": "pay_29QQoUBi66xm2f",
  "razorpayOrderId": "order_DBJOWzybf0sJbb",
  "razorpaySignature": "5f2d882124e2323ad3c21f4f9bff..."
}
*/

