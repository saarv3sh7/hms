package net.sarvesh.hms.service;

import lombok.RequiredArgsConstructor;
import net.sarvesh.hms.dto.PatientRequestDTO;
import net.sarvesh.hms.dto.PatientResponseDTO;
import net.sarvesh.hms.entity.Patient;
import net.sarvesh.hms.entity.User;
import net.sarvesh.hms.enums.Role;
import net.sarvesh.hms.exception.ResourceNotFoundException;
import net.sarvesh.hms.repository.PatientRepository;
import net.sarvesh.hms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createPatient(PatientRequestDTO request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PATIENT)
                .build();

        userRepository.save(user);

        Patient patient = new Patient();

        patient.setUser(user);
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());

        patientRepository.save(patient);
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(patient -> new PatientResponseDTO(
                        patient.getId(),
                        patient.getUser().getName(),
                        patient.getUser().getEmail(),
                        patient.getAge(),
                        patient.getGender()
                ))
                .toList();
    }

    @Override
    public void deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found"));

        patientRepository.delete(patient);
    }
}