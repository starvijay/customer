package com.example.satmunia.myapp;

/**
 * Created by SatMunia on 19-04-2018.
 */

public class SearchCustomer {

    private String name;
    private String email;
    private String mobile;
    private String location;

    public SearchCustomer(String name, String email, String mobile, String location) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.location =location;
    }
    public String getLocation() { return this.location;}
    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMobile() {
        return this.mobile;
    }
}
