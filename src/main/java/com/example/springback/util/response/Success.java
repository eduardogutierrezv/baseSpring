package com.example.springback.util.response;

public enum Success {

    OK("ok"), NOK("nok");

    private String data;

    Success(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
