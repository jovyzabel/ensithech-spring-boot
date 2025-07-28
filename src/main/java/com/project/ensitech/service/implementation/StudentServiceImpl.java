package com.project.ensitech.service.implementation;

import com.project.ensitech.exception.ResourceNotFoundException;
import com.project.ensitech.model.dto.StudentDto;
import com.project.ensitech.model.entity.Course;
import com.project.ensitech.model.entity.Student;
import com.project.ensitech.repository.CourseRepository;
import com.project.ensitech.repository.PersonRepository;
import com.project.ensitech.service.common.IStudentService;
import com.project.ensitech.service.mapper.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Injection de dépendances via le constructeur, gérée par Lombok. C'est moderne et propre.
public class StudentServiceImpl implements IStudentService {
    // Initialisation du logger pour cette classe en utilisant Log4j2
    private static final Logger log = LogManager.getLogger(StudentServiceImpl.class);

    private final PersonRepository personRepository;
    private final StudentMapper studentMapper;
    private final CourseRepository courseRepository;


    @Transactional // Les opérations d'écriture doivent être transactionnelles
    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        log.info("Tentative de création d'un nouvel étudiant avec email: {}", studentDto.getEmail());

        Student student =studentMapper.toEntity(studentDto );

        // --- LOGIQUE MÉTIER : Génération du matricule ---
        // Exemple : ENS- suivi de 8 caractères aléatoires en majuscules.
        String matricule = "ENS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        student.setMatricule(matricule);
        log.debug("Matricule généré pour l'étudiant {}: {}", studentDto.getEmail(), matricule);


        Student savedStudent = personRepository.save(student);
        log.info("Étudiant créé avec succès. ID: {}, Matricule: {}", savedStudent.getId(), savedStudent.getMatricule());

        return studentMapper.toDto(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents() {
        log.info("Récupération de tous les étudiants.");
        List<Student> students = personRepository.findAllStudentsWithCourses();
        log.info("{} étudiants trouvés.", students.size());
        return studentMapper.toDtoList(students);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto getStudentById(Long id) {
        log.info("Recherche de l'étudiant avec ID: {}", id);
        return personRepository.findStudentById(id)
                .map(studentMapper::toDto)
                .orElseThrow(() -> {
                    log.warn("Étudiant non trouvé avec ID: {}", id);
                    return new ResourceNotFoundException("Étudiant non trouvé avec l'identifiant : " + id);
                });
    }
    @Override
    @Transactional
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        log.info( " Tentative de mise à jour de l'étudiant avec l'id: {}",id);
        Student existStudent = personRepository.findStudentById(id)
                .orElseThrow(()->{
                    log.warn("Mise à jour a échouée, étudiant non trouvé avec ID: {}",id);
                    return new ResourceNotFoundException("Etudiant non trouvé avec id: " + id);
                });
        existStudent.setFirstName(studentDto.getFirstName());
        existStudent.setLastName(studentDto.getLastName());
        existStudent.setEmail(studentDto.getEmail());
        existStudent.setAddress(studentDto.getAddress());
        existStudent.setTelephone(studentDto.getTelephone());
        existStudent.setBirthday(studentDto.getBirthday());
        existStudent.setGender(studentDto.getGender());

        Student updateStudent = personRepository.save(existStudent);
        log.info("Mise à jour réussit de l'étudiant avec id : {}", id);
        return studentMapper.toDto(updateStudent);
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
    log.info("Tentative de suppression de l'étudiant avec id: {}",id);
        // On essaie de trouver l'entité. Si elle n'existe pas,
        // orElseThrow lèvera directement l'exception.

        Student studentToDelete = personRepository.findStudentById(id)
                .orElseThrow(() -> {
                    log.warn("Suppression échouée. étudiant non trouvé avec ID: {}", id);
                    return new ResourceNotFoundException("Impossible de supprimer. étudiant non trouvé avec l'identifiant : " + id);
                });

        // Si on arrive ici, l'étudiant existe. On peut le supprimer.
        personRepository.delete(studentToDelete);

        log.info("Suppression avec succès de l'étudiant avec ID: {}", id);
    }

    @Override
    @Transactional
    public StudentDto associateCoursesToStudent(Long studentId, List<Long> courseIds) {
        log.info("Association des cours {} à l'étudiant ID {}", courseIds, studentId);

        // 1. Récupérer l'étudiant
        Student student = personRepository.findStudentById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé avec l'ID : " + studentId));

        // 2. Récupérer les entités Course à partir de leurs IDs
        // Assurez-vous d'avoir un CourseRepository avec une entité Course dans ce service
        List<Course> coursesToAssociate = courseRepository.findAllById(courseIds);
        if (coursesToAssociate.size() != courseIds.size()) {
            log.warn("Certains cours n'ont pas été trouvés. IDs demandés: {}", courseIds);
            // Vous pouvez lancer une exception ici si vous le souhaitez
        }

        // 3. Mettre à jour l'association
        student.getCourses().clear(); // On supprime les anciennes associations
        student.getCourses().addAll(new HashSet<>(coursesToAssociate)); // On ajoute les nouvelles

        // 4. Sauvegarder l'étudiant
        Student updatedStudent = personRepository.save(student);
        log.info("Association réussie pour l'étudiant ID {}", studentId);

        return studentMapper.toDto(updatedStudent);
    }
}
