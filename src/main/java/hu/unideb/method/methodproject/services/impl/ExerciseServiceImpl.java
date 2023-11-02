package hu.unideb.method.methodproject.services.impl;

import hu.unideb.method.methodproject.dto.ExerciseDto;
import hu.unideb.method.methodproject.entities.Exercise;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.mapper.ExerciseMapper;
import hu.unideb.method.methodproject.repositories.ExerciseRepository;
import hu.unideb.method.methodproject.repositories.UserRepository;
import hu.unideb.method.methodproject.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExerciseMapper exerciseMapper;

    @Override
    public List<ExerciseDto> getAll() {
        return exerciseMapper.exerciseListToExerciseDtoList(exerciseRepository.findAll());
    }

    @Override
    public ExerciseDto findExerciseById(Long id) {
        Optional<Exercise> searched = exerciseRepository.findById(id);
        Exercise found = searched.get();
        found.setExercise(searched.get().getExercise());
        found.setTime(searched.get().getTime());
        return exerciseMapper.exerciseToExerciseDto(found);
    }

    @Override
    public ExerciseDto findByUserName(String name) {
        User user = userRepository.findUserByUsername(name);
        Exercise found = exerciseRepository.findExerciseByUser(user);
        if(found != null){
            return exerciseMapper.exerciseToExerciseDto(found);
        }
        Exercise exercise = new Exercise();
        exercise.setUser(user);
        return exerciseMapper.exerciseToExerciseDto(exercise);

    }

    @Override
    public void save(ExerciseDto exerciseDto) {
        exerciseRepository.save(exerciseMapper.exerciseDtoToExercise(exerciseDto));
    }

    @Override
    public void delete(ExerciseDto exerciseDto) {
        exerciseRepository.deleteById(exerciseDto.getId());
    }


}
