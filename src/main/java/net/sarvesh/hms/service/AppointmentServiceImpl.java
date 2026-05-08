package net.sarvesh.hms.service;

import lombok.RequiredArgsConstructor;
import net.sarvesh.hms.dto.AppointmentRequestDTO;
import net.sarvesh.hms.dto.AppointmentResponseDTO;
import net.sarvesh.hms.dto.AppointmentStatusUpdateDTO;
import net.sarvesh.hms.entity.Appointment;
import net.sarvesh.hms.entity.Doctor;
import net.sarvesh.hms.entity.Patient;
import net.sarvesh.hms.enums.AppointmentStatus;
import net.sarvesh.hms.exception.ResourceNotFoundException;
import net.sarvesh.hms.repository.AppointmentRepository;
import net.sarvesh.hms.repository.DoctorRepository;
import net.sarvesh.hms.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public void createAppointment(AppointmentRequestDTO request) {

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        boolean alreadyBooked =
                appointmentRepository.existsByDoctorAndAppointmentTime(
                        doctor,
                        request.getAppointmentTime()
                );

        if (alreadyBooked) {
            throw new RuntimeException("Doctor already booked for this time");
        }

        Appointment appointment = new Appointment();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setStatus(AppointmentStatus.SCHEDULED);

        appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAllAppointments() {

        return appointmentRepository.findAll()
                .stream()
                .map(app -> new AppointmentResponseDTO(
                        app.getId(),
                        app.getPatient().getUser().getName(),
                        app.getDoctor().getUser().getName(),
                        app.getAppointmentTime(),
                        app.getStatus()
                ))
                .toList();
    }

    @Override
    public void updateAppointmentStatus(
            Long id,
            AppointmentStatusUpdateDTO request
    ) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointment.setStatus(request.getStatus());

        appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {

        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found"));

        appointmentRepository.delete(appointment);
    }
}