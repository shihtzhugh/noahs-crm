package com.shihtzhugh.model.dto;

import com.shihtzhugh.model.Tool;

import java.math.BigDecimal;

public record CreateToolRequest(
        BigDecimal purchasePrice,
        String manual,
        String serialNumber,
        String modelNumber,
        String category,
        String brand
) {

    public static CreateToolRequest from(Tool tool) {
        return new CreateToolRequest(
                tool.getPurchasePrice(),
                tool.getManual(),
                tool.getSerialNumber(),
                tool.getModelNumber(),
                tool.getCategory(),
                tool.getBrand());
    }
}