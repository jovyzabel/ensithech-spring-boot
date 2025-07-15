package com.project.ensitech.service.implementation;


import com.project.ensitech.model.dto.TeacherDto;
import com.project.ensitech.model.entity.Teacher;
import com.project.ensitech.repository.PersonRepository;
import com.project.ensitech.service.common.ITeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor // Injection de dépendances via le constructeur, gérée par Lombok. C'est moderne et propre.
public class TeacherServiceImpl implements ITeacherService {
    private final PersonRepository personRepository;
    /**
     * Crée un nouvel enseignant à partir des données d'un DTO.
     *
     * @param dto Les informations du nouvel enseignant.
     * @return L'entité Teacher qui a été sauvegardée.
     */
    @Override
    public Teacher createTeacher(TeacherDto dto) {
        // Crée une nouvelle instance de l'entité Teacher
        Teacher teacher = new Teacher();

        // Mappe les données du DTO vers l'entité
        teacher.setFirstName(dto.getFirstName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setAddress(dto.getAddress());
        teacher.setTelephone(dto.getTelephone());
        teacher.setBirthday(dto.getBirthday());
        teacher.setGender(dto.getGender()); // Ne pas oublier le genre !

        // Définit la date de création côté serveur pour garantir sa validité
        teacher.setCreatedAt(new Date());

        // Sauvegarde l'entité en base de données via le repository et la retourne.
        return personRepository.save(teacher);
    }

    /**
     * Récupère la liste de tous les enseignants.
     *
     * @return une liste d'entités Teacher.
     */
    @Override
    public List<Teacher> getAllTeachers() {
        // Appelle la méthode optimisée du repository.
        return personRepository.findAllTeachers();
    }

    /**
     * Récupère un enseignant par son identifiant.
     *
     * @param id L'identifiant de l'enseignant.
     * @return L'enseignant trouvé.
     * @throws RuntimeException si aucun enseignant n'est trouvé avec cet ID.
     */
    @Override
    public Teacher getTeacherById(Long id) {
        // Utilise la méthode spécifique pour trouver un Teacher.
        // .orElseThrow() est une manière élégante de gérer le cas où l'entité n'est pas trouvée.
        return personRepository.findTeacherById(id)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));
    }

    /**
     * Met à jour les informations d'un enseignant existant.
     *
     * @param id  L'identifiant de l'enseignant à mettre à jour.
     * @param dto Les nouvelles informations.
     * @return L'entité Teacher mise à jour.
     */
    @Override
    public Teacher updateTeacher(Long id, TeacherDto dto) {
        // 1. On récupère l'enseignant existant. Si non trouvé, une exception sera levée.
        Teacher existingTeacher = getTeacherById(id);

        // 2. On met à jour ses champs avec les nouvelles données du DTO.
        existingTeacher.setFirstName(dto.getFirstName());
        existingTeacher.setLastName(dto.getLastName());
        existingTeacher.setEmail(dto.getEmail());
        existingTeacher.setAddress(dto.getAddress());
        existingTeacher.setTelephone(dto.getTelephone());
        existingTeacher.setBirthday(dto.getBirthday());
        existingTeacher.setGender(dto.getGender());

        // 3. On sauvegarde l'entité mise à jour. JPA est assez intelligent pour savoir
        // qu'il doit faire un UPDATE SQL car l'entité a déjà un ID.
        return personRepository.save(existingTeacher);
    }

    /**
     * Supprime un enseignant par son identifiant.
     *
     * @param id L'identifiant de l'enseignant à supprimer.
     */
    @Override
    public void deleteTeacher(Long id) {
        // 1. On vérifie d'abord si l'enseignant existe.
        if (!personRepository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete. Teacher not found with id: " + id);
        }
        // 2. On le supprime.
        personRepository.deleteById(id);

    }
}
