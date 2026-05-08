package net.sarvesh.hms.service;

import net.sarvesh.hms.dto.PatientRequestDTO;
import net.sarvesh.hms.dto.PatientResponseDTO;

import java.util.List;

public interface PatientService {

    void createPatient(PatientRequestDTO request);

    List<PatientResponseDTO> getAllPatients();

    void deletePatient(Long id);
}