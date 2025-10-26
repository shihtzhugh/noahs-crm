package com.shihtzhugh.model.dto;

import com.shihtzhugh.model.Vehicle;

import java.math.BigDecimal;

public record VehicleDto(
        BigDecimal purchasePrice,
        String manual,
        String vin,
        String make,
        String model,
        String engine,
        String licensePlate
) {

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
}
