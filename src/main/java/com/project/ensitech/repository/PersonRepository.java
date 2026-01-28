package com.project.ensitech.repository;

import com.project.ensitech.model.entity.*;
import com.project.ensitech.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
// Le repository hérite de JpaRepository pour l'entité de base 'Person'.
// Il pourra donc gérer les Person, Teacher, Student, etc.
public interface PersonRepository extends JpaRepository<Person, Long>{
    /**
     * Utilise une requête JPQL avec un CONSTRUCTEUR pour s'assurer que tous les champs,
     * y compris l'ID hérité, sont correctement chargés.
     */
    @Query("SELECT new com.project.ensitech.model.entity.Teacher(t.id, t.firstName, t.lastName, t.email, t.address, t.telephone, t.birthday, t.gender, t.createdAt) FROM Teacher t")
    List<Teacher> findAllTeachers();

    @Query("SELECT DISTINCT t FROM Teacher t LEFT JOIN FETCH t.courses")
    List<Teacher> findAllTeachersWithCourses();

    /**
     * Trouve un enseignant par son ID.
     * Cette méthode garantit que l'objet retourné est bien un Teacher.
     *
     * @param id L'identifiant de l'enseignant.
     * @return un Optional contenant l'enseignant s'il est trouvé et est bien un enseignant.
     */
    @Query("SELECT t FROM Teacher t WHERE t.id = :id")
    Optional<Teacher> findTeacherById(Long id);

    @Query("SELECT t FROM Teacher t LEFT JOIN FETCH t.courses WHERE t.id = :id")
    Optional<Teacher> findByIdWithCourses(@Param("id") Long id);


    @Query("SELECT t FROM Course c JOIN c.teacher t LEFT JOIN FETCH t.courses WHERE c.id = :courseId")
    Optional<Teacher> findTeacherWithCoursesByCourseId(@Param("courseId") Long courseId);


    // Pour les Étudiants
//    @Query("SELECT s FROM Student s")
//    List<Student> findAllStudents();

    /**
     * Récupère tous les étudiants en chargeant également leurs cours associés
     * pour éviter les problèmes de LazyInitializationException.
     * 'JOIN FETCH s.courses' est la clé : il dit à Hibernate de faire une jointure
     * et de remplir la collection 'courses' dans la même requête.
     * 'DISTINCT' est important pour éviter les étudiants dupliqués si un étudiant a plusieurs cours.
     */
    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses")
    List<Student> findAllStudentsWithCourses();

    @Query("SELECT s FROM Student s WHERE s.id = :id")
    Optional<Student> findStudentById(Long id);

    // Pour les StudyManagers
    @Query("SELECT sm FROM StudyManager sm")
    List<StudyManager> findAllStudyManagers();

    @Query("SELECT sm FROM StudyManager sm WHERE sm.id = :id")
    Optional<StudyManager> findStudyManagerById(Long id);

    // Pour les Directors
    @Query("SELECT d FROM Director d")
    List<Director> findAllDirectors();

    @Query("SELECT d FROM Director d WHERE d.id = :id")
    Optional<Director> findDirectorById(Long id);

    // Pour les Administrators
    @Query("SELECT a FROM Administrator a")
    List<Administrator> findAllAdministrators();

    @Query("SELECT a FROM Administrator a WHERE a.id = :id")
    Optional<Administrator> findAdministratorById(Long id);
}