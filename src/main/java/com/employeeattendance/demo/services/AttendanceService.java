package com.employeeattendance.demo.services;

import com.employeeattendance.demo.models.Attendance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AttendanceService {
    Attendance takeAttendance(Attendance attendance);
    List<Attendance> getAttendanceByEmployeeId(Long id);
}
