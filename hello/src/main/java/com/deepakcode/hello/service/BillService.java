package com.deepakcode.hello.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepakcode.hello.dto.PaymentVerificationDTO;
import com.deepakcode.hello.entity.Bill;
import com.deepakcode.hello.entity.BillItem;
import com.deepakcode.hello.entity.Patient;
import com.deepakcode.hello.repository.BillRepository;
import com.deepakcode.hello.repository.PatientRepository;
import com.razorpay.RazorpayException;

import org.springframework.beans.factory.annotation.Value;

// Bill Service
@Service
public class BillService {
  @Autowired private BillRepository billRepo;
  @Autowired private PatientRepository patientRepo;
  @Autowired private RazorpayService razorpayService;
  @Autowired private EmailService emailService;
  @Value("${razorpay.key_id}") private String keyId;

  public Bill generateBill(Long patientId, List<BillItem> items) {
    Patient patient = patientRepo.findById(patientId).orElseThrow(() -> new RuntimeException("Patient not found"));

    Bill bill = new Bill();
    bill.setPatient(patient);
    bill.setDateGenerated(LocalDateTime.now());
    bill.setItems(items);
    bill.setStatus("UNPAID");

    items.forEach(item -> item.setBill(bill));
    double total = items.stream().mapToDouble(BillItem::getAmount).sum();
    bill.setTotalAmount(total);

    try {
      String orderId = razorpayService.createOrder(total);
      bill.setOrderId(orderId);
    } catch (RazorpayException e) {
      throw new RuntimeException("Failed to create payment order", e);
    }

    Bill saved = billRepo.save(bill);
    emailService.sendInvoiceEmail(saved, keyId);
    return saved;
  }

  public boolean verifyAndMarkPaid(PaymentVerificationDTO dto) {
    boolean isValid = razorpayService.verifyPayment(
        dto.getRazorpayOrderId(),
        dto.getRazorpayPaymentId(),
        dto.getRazorpaySignature()
    );
    if (isValid) {
      Bill bill = billRepo.findByOrderId(dto.getRazorpayOrderId())
        .orElseThrow(() -> new RuntimeException("Bill not found"));
      bill.setStatus("PAID");
      billRepo.save(bill);
    }
    return isValid;
  }
}

