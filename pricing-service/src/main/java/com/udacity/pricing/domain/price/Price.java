package com.udacity.pricing.domain.price;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents the price of a given vehicle, including currency.
 */
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long vehicleId;

    private String currency;
    private BigDecimal price;

    public Price() {
    }

    public Price(Long vehicleId, String currency, BigDecimal price ) {
        this.vehicleId = vehicleId;
        this.currency = currency;
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        return "Price{" +
                "vehicleId=" + vehicleId +
                ", currency='" + currency + '\'' +
                ", price=" + price +
                '}';
    }
}
