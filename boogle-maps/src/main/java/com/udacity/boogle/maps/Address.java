package com.udacity.boogle.maps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Declares a class to store an address, city, state and zip code.
 */
@Getter
@Setter
@NoArgsConstructor
public class Address {

    private String address;
    private String city;
    private String state;
    private String zip;

    public Address(String address, String city, String state, String zip) {
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }
}
