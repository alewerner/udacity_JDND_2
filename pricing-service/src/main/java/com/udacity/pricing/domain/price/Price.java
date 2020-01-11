package com.udacity.pricing.domain.price;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents the price of a given vehicle, including currency.
 */
@EnableEurekaClient
public class Price {

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;
        Price price1 = (Price) o;
        return Objects.equals(getVehicleId(), price1.getVehicleId()) &&
                Objects.equals(getCurrency(), price1.getCurrency()) &&
                Objects.equals(getPrice(), price1.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVehicleId(), getCurrency(), getPrice());
    }
}
