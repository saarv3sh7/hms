package net.sarvesh.hms.service;

import net.sarvesh.hms.dto.DoctorRequestDTO;
import net.sarvesh.hms.dto.DoctorResponseDTO;

import java.util.List;

public interface DoctorService {

    void createDoctor(DoctorRequestDTO request);

    List<DoctorResponseDTO> getAllDoctors();

    void deleteDoctor(Long id);
}