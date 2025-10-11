package com.shihtzhugh.service;

import com.shihtzhugh.model.Vehicle;
import com.shihtzhugh.model.dto.VehicleDto;
import com.shihtzhugh.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle createVehicle(VehicleDto vehicleDto) {
        return vehicleRepository.save(Vehicle.from(vehicleDto));
    }
}
