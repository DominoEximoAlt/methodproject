package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.ExerciseDto;
import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.enums.ExerciseEnum;
import hu.unideb.method.methodproject.mapper.ExerciseMapper;
import hu.unideb.method.methodproject.mapper.UserMapper;
import hu.unideb.method.methodproject.services.ExerciseService;
import hu.unideb.method.methodproject.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.faces.bean.ViewScoped;
import java.util.*;
import java.util.logging.Logger;

@Controller
@ViewScoped
public class ExcerciseView {

    Logger logger = Logger.getLogger(ExcerciseView.class.getName());

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ExerciseMapper exerciseMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    private Map<String, String> exercises = new HashMap<>();

    private String exercise;
    private ExerciseDto currentExercise;

    private List<ExerciseDto> exerciseDtoList;

    @PostConstruct
    public void init(){
        exerciseDtoList = new ArrayList<>();
        currentExercise = new ExerciseDto();
        exercises.put("RUNNING","RUNNING");
        exercises.put("SQUATS","SQUATS");
        exercises.put("WALKING","WALKING");
        exercises.put("STRENGTH_TRAINING","STRENGTH_TRAINING");
        exercises.put("ENDURANCE","ENDURANCE");
        exercises.put("SWIMMING","SWIMMING");
        exercises.put("JUMP_ROPE","JUMP_ROPE");
    }

    public Map<String, String> getExercises() {
        return exercises;
    }

    public void setExercises(Map<String, String> exercises) {
        this.exercises = exercises;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public ExerciseDto getCurrentExercise() {
        return currentExercise;
    }

    public void setCurrentExercise(ExerciseDto currentExercise) {
        this.currentExercise = currentExercise;
    }

    public List<ExerciseDto> getExerciseDtoList() {
        return exerciseDtoList;
    }

    public void setExerciseDtoList(List<ExerciseDto> exerciseDtoList) {
        this.exerciseDtoList = exerciseDtoList;
    }


    public void addExercise(UserDto userDto){
        User user = userMapper.userDtoToUser(userService.findUserByUserName(userDto.getUsername()));
        currentExercise.setUser(user);
        currentExercise.setExercise(Enum.valueOf(ExerciseEnum.class,exercise));
        exerciseService.save(currentExercise);
        exerciseDtoList.add(currentExercise);

    }
}
