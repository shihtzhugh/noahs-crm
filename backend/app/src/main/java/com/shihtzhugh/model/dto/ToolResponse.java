package com.shihtzhugh.model.dto;

import com.shihtzhugh.model.Tool;

import java.math.BigDecimal;

public record ToolResponse(
        Long id,
        String imageUri,
        BigDecimal purchasePrice,
        String manual,
        String serialNumber,
        String modelNumber,
        String category,
        String brand
) {

    public static ToolResponse from(Tool tool) {
        return new ToolResponse(
                tool.getId(),
                tool.getImageUri(),
                tool.getPurchasePrice(),
                tool.getManual(),
                tool.getSerialNumber(),
                tool.getModelNumber(),
                tool.getCategory(),
                tool.getBrand());

    }
}