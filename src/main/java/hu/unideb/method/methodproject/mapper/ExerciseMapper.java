package hu.unideb.method.methodproject.mapper;

import hu.unideb.method.methodproject.dto.ExerciseDto;
import hu.unideb.method.methodproject.entities.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    ExerciseDto exerciseToExerciseDto(Exercise exercise);

    Exercise exerciseDtoToExercise(ExerciseDto exerciseDto);

    List<ExerciseDto> exerciseListToExerciseDtoList(List<Exercise> exerciseList);

    List<Exercise> exerciseDtoListToExerciseList(List<ExerciseDto> exerciseDtoList);
}
