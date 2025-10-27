package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private String product;
    private int qty;
    private double price;

    // constructor, getters, setters
}