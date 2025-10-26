package com.shihtzhugh.controller;

import com.shihtzhugh.model.Vehicle;
import com.shihtzhugh.model.dto.VehicleDto;
import com.shihtzhugh.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {
        var vehicleList = vehicleService.findAllVehicles();
        return ResponseEntity.ok(
                vehicleList.stream()
                        .map(VehicleDto::from)
                        .toList());
    }

    @PostMapping
    public ResponseEntity<VehicleDto> createVehicle(@RequestBody VehicleDto vehicleDto) {
        var vehicle = Vehicle.from(vehicleDto);
        var savedVehicle = vehicleService.saveVehicle(vehicle);
        return ResponseEntity.ok(VehicleDto.from(savedVehicle));
    }
}
