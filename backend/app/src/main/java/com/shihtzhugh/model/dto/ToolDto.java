package com.shihtzhugh.model.dto;

import com.shihtzhugh.model.Tool;

import java.math.BigDecimal;

public record ToolDto(
        BigDecimal purchasePrice,
        String manual,
        String serialNumber,
        String modelNumber,
        String category,
        String brand
) {

    public static ToolDto from(Tool tool) {
        return new ToolDto(
                tool.getPurchasePrice(),
                tool.getManual(),
                tool.getSerialNumber(),
                tool.getModelNumber(),
                tool.getCategory(),
                tool.getBrand());
    }
}