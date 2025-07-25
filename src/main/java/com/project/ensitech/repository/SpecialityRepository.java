package com.project.ensitech.repository;

import com.project.ensitech.model.entity.Speciality;
import com.project.ensitech.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {


    @Query("SELECT s FROM Speciality s WHERE s.id = :id")
    Speciality findSpecialityById(int id);

    @Query("SELECT s FROM Speciality s")
    List<Speciality> findAllSpecialities();
}
