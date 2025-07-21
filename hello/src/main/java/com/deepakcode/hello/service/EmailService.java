package com.deepakcode.hello.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.deepakcode.hello.entity.Bill;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendInvoiceEmail(Bill bill, String keyId) {
        String patientName = bill.getPatient().getName();
        String orderId = bill.getOrderId();
        Double amount = bill.getTotalAmount();

        String payHtml = """
        <html>
          <body>
            <h3>Hello %s,</h3>
            <p>Your bill of ₹%.2f is generated. Please click below to pay securely.</p>
            <button onclick=\\"payNow()\\">Pay Now</button>
            <script src=\\"https://checkout.razorpay.com/v1/checkout.js\\"></script>
            <script>
              function payNow() {
                var options = {
                  \\"key\\": \\"%s\\",
                  \\"amount\\": %d,
                  \\"currency\\": \\"INR\\",
                  \\"name\\": \\"XYZ Hospital\\",
                  \\"description\\": \\"Patient Bill Payment\\",
                  \\"order_id\\": \\"%s\\",
                  \\"handler\\": function (response) {
                    alert('Payment Successful!');
                    console.log(response);
                  }
                };
                var rzp = new Razorpay(options);
                rzp.open();
              }
            </script>
          </body>
        </html>
        """.formatted(patientName, amount, keyId, (int) (amount * 100), orderId);

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(bill.getPatient().getEmail());
            helper.setSubject("Your Hospital Bill – Pay Online");
            helper.setText(payHtml, true); // true = HTML email
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
