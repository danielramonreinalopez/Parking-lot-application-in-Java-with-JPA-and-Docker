package com.example.parking.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.parking.models.ParkingSpot;
@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
}
