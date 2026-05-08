package net.sarvesh.hms.service;

import net.sarvesh.hms.dto.AppointmentRequestDTO;
import net.sarvesh.hms.dto.AppointmentResponseDTO;
import net.sarvesh.hms.dto.AppointmentStatusUpdateDTO;

import java.util.List;

public interface AppointmentService {

    void createAppointment(AppointmentRequestDTO request);

    List<AppointmentResponseDTO> getAllAppointments();

    void updateAppointmentStatus(
            Long id,
            AppointmentStatusUpdateDTO request
    );

    void deleteAppointment(Long id);
}