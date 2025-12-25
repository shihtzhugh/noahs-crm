package com.shihtzhugh.model;

import com.shihtzhugh.model.dto.CreateToolRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imageUri;
    private BigDecimal purchasePrice;
    private String manual;
    private String serialNumber;
    private String modelNumber;
    private String category;
    private String brand;

    protected Tool() {
    }

    public Tool(
            BigDecimal purchasePrice,
            String manual,
            String serialNumber,
            String modelNumber,
            String category,
            String brand
    ) {
        this.purchasePrice = purchasePrice;
        this.manual = manual;
        this.serialNumber = serialNumber;
        this.modelNumber = modelNumber;
        this.category = category;
        this.brand = brand;
    }

    public static Tool from(CreateToolRequest createToolRequest) {
        return new Tool(
                createToolRequest.purchasePrice(),
                createToolRequest.manual(),
                createToolRequest.serialNumber(),
                createToolRequest.modelNumber(),
                createToolRequest.category(),
                createToolRequest.brand()
        );
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String image) {
        this.imageUri = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
