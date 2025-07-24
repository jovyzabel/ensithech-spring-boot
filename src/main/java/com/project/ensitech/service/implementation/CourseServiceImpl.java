package com.project.ensitech.service.implementation;

import com.project.ensitech.exception.ResourceNotFoundException;
import com.project.ensitech.model.dto.CourseDto;
import com.project.ensitech.model.entity.Course;
import com.project.ensitech.model.entity.Teacher;
import com.project.ensitech.repository.CourseRepository;
import com.project.ensitech.repository.PersonRepository;
import com.project.ensitech.service.common.ICourseService;
import com.project.ensitech.service.mapper.CourseMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;
    //private final TeacherRepository teacherRepository;
    private final PersonRepository personRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseDto createCourse(CourseDto dto) {
        Course course = courseMapper.toEntity(dto);
        if (dto.getTeacherId() != null) {
            Teacher teacher = (Teacher) personRepository.findById(dto.getTeacherId())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            course.setTeacher(teacher);
        }
        return courseMapper.toDto(courseRepository.save(course));
    }
    @Override
    public CourseDto updateCourse( CourseDto courseDto) {
        Course existing = courseRepository.findById(courseDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + courseDto.getId()));

        // Update fields
        existing.setIntitule(courseDto.getIntitule());
        existing.setCoefficient(courseDto.getCoefficient());
        existing.setNombreHeures(courseDto.getNombreHeures());


        System.out.println("new id "+ courseDto.getTeacherId());
        // Mise à jour du teacher si l'ID change
        if (courseDto.getTeacherId() != null) {
            Long existingTeacherId = (existing.getTeacher() != null) ? existing.getTeacher().getId() : null;
            System.out.println("existing id"+ existingTeacherId);
            if (!Objects.equals(existingTeacherId, courseDto.getTeacherId())) {
                Teacher teacher = (Teacher) personRepository.findById(courseDto.getTeacherId())
                        .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + courseDto.getTeacherId()));
                existing.setTeacher(teacher);

            }
        } else {
            // Si teacherId est null dans le DTO, on supprime l'association
            existing.setTeacher(null);
        }


        Course updated = courseRepository.save(existing);

        return courseMapper.toDto(updated);
    }

    @Override
    public CourseDto getCourse(Long id) {
        Course course = courseRepository.findById(id)
        //Course course = courseRepository.findByIdWithTeacher(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cours non trouvé avec id: " + id));
       CourseDto courseDto=  courseMapper.toDto(course);
        return courseDto;
    }

    @Override
    public CourseDto assignTeacherToCourse(Long courseId, Long teacherId) {
        // Récupérer le cours
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Cours non trouvé avec id " + courseId));

        // Récupérer l'enseignant
        Teacher teacher = (Teacher) personRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Enseignant non trouvé avec id " + teacherId));

        // Associer l'enseignant au cours
        course.setTeacher(teacher);

        // Sauvegarder les changements
        course = courseRepository.save(course);

        // Retourner le DTO
        return courseMapper.toDto(course);
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                //courseRepository.findAllWithTeacher().stream()
                .map(courseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cours non trouvé avec id: " + id);
        }
        courseRepository.deleteById(id);
    }
}