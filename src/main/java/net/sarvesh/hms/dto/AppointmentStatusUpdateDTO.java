package net.sarvesh.hms.dto;

import lombok.Getter;
import lombok.Setter;
import net.sarvesh.hms.enums.AppointmentStatus;

@Getter
@Setter
public class AppointmentStatusUpdateDTO {

    private AppointmentStatus status;
}