package com.example.parking.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.parking.models.Vehicle;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}