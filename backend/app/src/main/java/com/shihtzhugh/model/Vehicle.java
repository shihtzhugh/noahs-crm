package com.shihtzhugh.model;

import com.shihtzhugh.model.dto.VehicleDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal purchasePrice;
    private String manual;
    private String vin;
    private String make;
    private String model;
    private String engine;
    private String licensePlate;

    protected Vehicle() {}

    public Vehicle(BigDecimal purchasePrice, String manual, String vin, String make, String model, String engine, String licensePlate) {
        this.purchasePrice = purchasePrice;
        this.manual = manual;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.licensePlate = licensePlate;
    }

    public static Vehicle from(VehicleDto vehicleDto) {
        return new Vehicle(
                vehicleDto.purchasePrice(),
                vehicleDto.manual(),
                vehicleDto.vin(),
                vehicleDto.make(),
                vehicleDto.model(),
                vehicleDto.engine(),
                vehicleDto.licensePlate()
        );
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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
