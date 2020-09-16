package com.employeeattendance.demo.repositories;

import com.employeeattendance.demo.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    Iterable<Attendance> findAllByEmployeeId (Long id);
}
