package com.example.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Invoice {
    private String invoiceNumber;
    private String customerName;
    private List<Item> items;
}
