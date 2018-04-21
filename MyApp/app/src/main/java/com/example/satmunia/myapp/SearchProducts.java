package com.example.satmunia.myapp;

/**
 * Created by SatMunia on 19-04-2018.
 */

public class SearchProducts {

    private String rank;
    private String country;
    private String product;

    public SearchProducts(String rank, String country, String product) {
        this.rank = rank;
        this.country = country;
        this.product = product;
    }

    public String getRank() {
        return this.rank;
    }

    public String getCountry() {
        return this.country;
    }

    public String getProduct() {
        return this.product;
    }
}
