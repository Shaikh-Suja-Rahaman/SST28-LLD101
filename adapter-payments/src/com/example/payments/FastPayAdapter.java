package com.example.payments;

public class FastPayAdapter implements PaymentGateway{
  private final FastPayClient fp;

  FastPayAdapter(FastPayClient fp){ //making sure that O/C is not messed up

    this.fp = fp;
  }

  // FastPayClient
  @Override
  public String charge(String customerId, int amountCents){
    return fp.payNow(customerId, amountCents);
  }
}
