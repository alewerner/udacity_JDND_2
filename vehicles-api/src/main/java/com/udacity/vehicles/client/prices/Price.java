package com.udacity.vehicles.client.prices;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Represents the price of a given vehicle, including currency.
 */
@Getter
@Setter
@NoArgsConstructor
public class Price {

    private String currency;

    private BigDecimal price;

    private Long vehicleId;

    public Price(String currency, BigDecimal price, Long vehicleId) {
        this.currency = currency;
        this.price = price;
        this.vehicleId = vehicleId;
    }

    public Price(String price) {
        String[] priceInfo = price.split(" ");
        this.currency = priceInfo[0];
        this.price = new BigDecimal(priceInfo[1]);
    }

    @Override
    public String toString() {
        return "Price{" +
                "currency='" + currency + '\'' +
                ", price=" + price +
                ", vehicleId=" + vehicleId +
                '}';
    }
}
