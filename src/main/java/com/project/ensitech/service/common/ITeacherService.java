package com.project.ensitech.service.common;

import com.project.ensitech.model.dto.TeacherDto;
import com.project.ensitech.model.entity.Teacher;

import java.util.List;

public interface ITeacherService {
    /**
     * Crée un nouvel enseignant à partir des données d'un DTO.
     * @param dto Les informations du nouvel enseignant.
     * @return L'entité Teacher qui a été sauvegardée.
     */
    Teacher createTeacher(TeacherDto dto);
    /**
     * Récupère la liste de tous les enseignants.
     * @return une liste d'entités Teacher.
     */
    List<Teacher> getAllTeachers();

    /**
     * Récupère un enseignant par son identifiant.
     * @param id L'identifiant de l'enseignant.
     * @return L'enseignant trouvé.
     * @throws RuntimeException si aucun enseignant n'est trouvé avec cet ID.
     */
    Teacher getTeacherById(Long id);

    /**
     * Met à jour les informations d'un enseignant existant.
     * @param id L'identifiant de l'enseignant à mettre à jour.
     * @param dto Les nouvelles informations.
     * @return L'entité Teacher mise à jour.
     */
    Teacher updateTeacher(Long id, TeacherDto dto);

    /**
     * Supprime un enseignant par son identifiant.
     * @param id L'identifiant de l'enseignant à supprimer.
     */
    void deleteTeacher(Long id);
}
