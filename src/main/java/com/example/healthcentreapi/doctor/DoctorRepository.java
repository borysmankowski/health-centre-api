package com.example.healthcentreapi.doctor;

import com.example.healthcentreapi.doctor.model.Doctor;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT v FROM Doctor v WHERE v.id = :id")
    Optional<Doctor> findDoctorByIdForLock(@Param("id") long id);
}
