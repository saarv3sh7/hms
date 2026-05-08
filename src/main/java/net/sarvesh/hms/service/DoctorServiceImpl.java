package net.sarvesh.hms.service;

import lombok.RequiredArgsConstructor;
import net.sarvesh.hms.dto.DoctorRequestDTO;
import net.sarvesh.hms.dto.DoctorResponseDTO;
import net.sarvesh.hms.entity.Doctor;
import net.sarvesh.hms.entity.User;
import net.sarvesh.hms.enums.Role;
import net.sarvesh.hms.exception.ResourceNotFoundException;
import net.sarvesh.hms.repository.DoctorRepository;
import net.sarvesh.hms.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createDoctor(DoctorRequestDTO request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.DOCTOR)
                .build();

        userRepository.save(user);

        Doctor doctor = new Doctor();
        doctor.setUser(user);
        doctor.setSpecialization(request.getSpecialization());
        doctor.setExperience(request.getExperience());

        doctorRepository.save(doctor);
    }

    @Override
    public List<DoctorResponseDTO> getAllDoctors() {

        return doctorRepository.findAll()
                .stream()
                .map(doc -> new DoctorResponseDTO(
                        doc.getId(),
                        doc.getUser().getName(),
                        doc.getUser().getEmail(),
                        doc.getSpecialization(),
                        doc.getExperience()
                ))
                .toList();
    }

    @Override
    public void deleteDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found"));

        doctorRepository.delete(doctor);
    }
}