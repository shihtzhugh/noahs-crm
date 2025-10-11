package com.shihtzhugh.model.dto;

import com.shihtzhugh.model.Vehicle;

import java.math.BigDecimal;

public class VehicleDto {

    private final BigDecimal purchasePrice;
    private final String manual;
    private final String vin;
    private final String make;
    private final String model;
    private final String engine;
    private final String licensePlate;

    public VehicleDto(BigDecimal purchasePrice, String manual, String vin, String make, String model, String engine, String licensePlate) {
        this.purchasePrice = purchasePrice;
        this.manual = manual;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.licensePlate = licensePlate;
    }

    public static VehicleDto from(Vehicle vehicle) {
        return new VehicleDto(
                vehicle.getPurchasePrice(),
                vehicle.getManual(),
                vehicle.getVin(),
                vehicle.getMake(),
                vehicle.getModel(),
                vehicle.getEngine(),
                vehicle.getLicensePlate());
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public String getManual() {
        return manual;
    }

    public String getVin() {
        return vin;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getEngine() {
        return engine;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
