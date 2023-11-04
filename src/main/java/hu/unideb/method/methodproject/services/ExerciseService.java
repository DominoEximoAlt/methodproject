package hu.unideb.method.methodproject.services;

import hu.unideb.method.methodproject.dto.ExerciseDto;

import java.util.List;

public interface ExerciseService {

    List<ExerciseDto> getAll();
    ExerciseDto findExerciseById(Long id);
    ExerciseDto findByUserName(String name);
    void save(ExerciseDto exerciseDto);
    void delete(ExerciseDto exerciseDto);

}
