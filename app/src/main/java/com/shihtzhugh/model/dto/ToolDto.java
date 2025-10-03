package com.shihtzhugh.model.dto;

import com.shihtzhugh.model.Tool;

import java.math.BigDecimal;

public class ToolDto {

    private final BigDecimal purchasePrice;
    private final String manual;
    private final String serialNumber;
    private final String modelNumber;
    private final String category;
    private final String brand;

    public ToolDto(BigDecimal purchasePrice, String manual, String serialNumber, String modelNumber, String category, String brand) {
        this.purchasePrice = purchasePrice;
        this.manual = manual;
        this.serialNumber = serialNumber;
        this.modelNumber = modelNumber;
        this.category = category;
        this.brand = brand;
    }

    public static ToolDto from(Tool tool) {
        return new ToolDto(
                tool.getPurchasePrice(),
                tool.getManual(),
                tool.getSerialNumber(),
                tool.getModelNumber(),
                tool.getCategory(),
                tool.getBrand());
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public String getManual() {
        return manual;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }
}