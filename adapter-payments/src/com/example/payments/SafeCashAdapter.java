package com.example.payments;

public class SafeCashAdapter implements PaymentGateway{
  private final SafeCashClient sc;
  SafeCashAdapter(SafeCashClient sc){
    this.sc = sc;
  }

  // SafeCashClient sc = new SafeCashClient();

  @Override
  public String charge(String customerId, int amountCents){
    return sc.createPayment(amountCents, customerId).confirm();
  }
}
