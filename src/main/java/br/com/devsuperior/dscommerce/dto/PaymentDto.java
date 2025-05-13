package br.com.devsuperior.dscommerce.dto;

import br.com.devsuperior.dscommerce.entitites.Payment;

import java.time.Instant;

public class PaymentDto {
    private Long id;
    private Instant moment;

    public PaymentDto() {}

    public PaymentDto(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDto(Payment payment) {
        id = payment.getId();
        moment = payment.getMoment();
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
