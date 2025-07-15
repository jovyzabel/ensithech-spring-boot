package com.project.ensitech.repository;

import com.project.ensitech.model.entity.Person;
import com.project.ensitech.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// Le repository hérite de JpaRepository pour l'entité de base 'Person'.
// Il pourra donc gérer les Person, Teacher, Student, etc.
public interface PersonRepository extends JpaRepository<Person, Long>{
    /**
     * Utilise une requête JPQL (Java Persistence Query Language) pour sélectionner
     * spécifiquement les entités de type Teacher. C'est beaucoup plus performant
     * que de tout récupérer et de filtrer en Java.
     * Hibernate traduira "FROM Teacher" en "FROM person WHERE person_type = 1".
     *
     * @return Une liste de tous les enseignants.
     */
    @Query("SELECT t FROM Teacher t")
    List<Teacher> findAllTeachers();

    /**
     * Trouve un enseignant par son ID.
     * Cette méthode garantit que l'objet retourné est bien un Teacher.
     *
     * @param id L'identifiant de l'enseignant.
     * @return un Optional contenant l'enseignant s'il est trouvé et est bien un enseignant.
     */
    @Query("SELECT t FROM Teacher t WHERE t.id = :id")
    Optional<Teacher> findTeacherById(Long id);
}