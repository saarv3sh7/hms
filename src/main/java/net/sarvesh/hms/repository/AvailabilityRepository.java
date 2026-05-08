package net.sarvesh.hms.repository;

import net.sarvesh.hms.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findByDoctorIdAndDate(Long doctorId, LocalDate date);
}