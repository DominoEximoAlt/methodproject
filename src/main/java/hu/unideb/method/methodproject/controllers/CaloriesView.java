package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.CaloriesDTO;
import hu.unideb.method.methodproject.dto.ExerciseDto;
import hu.unideb.method.methodproject.enums.ExerciseEnum;
import hu.unideb.method.methodproject.services.ExerciseService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

@Controller
@ApplicationScope
public class CaloriesView {

    @Autowired
    ExerciseService exerciseService;

    CaloriesDTO currentCalories;

    List<CaloriesDTO> caloriesDTOList;

    @PostConstruct
    public void init(){
        currentCalories = new CaloriesDTO();
        caloriesDTOList = new ArrayList<>();
    }

    public void addFromExcercise(ExerciseDto exerciseDto){
        int caloricValue = 0;

        switch (exerciseDto.getExercise()){
            case ENDURANCE :
                caloricValue = 5;
                break;
            case WALKING:
                caloricValue = 2;
                break;
            case RUNNING:
                caloricValue = 10;
                break;
            case JUMP_ROPE:
                caloricValue = 7;
                break;
            case STRENGTH_TRAINING:
                caloricValue = 5;
                break;
            case SQUATS:
                caloricValue = 3;
                break;
            case SWIMMING:
                caloricValue = 8;
                break;

        }

        currentCalories.setCaloriesFromExercise(currentCalories.getCaloriesFromExercise() + (exerciseDto.getTime().intValue() * caloricValue));
    }

    public CaloriesDTO getCurrentCalories() {
        return currentCalories;
    }

    public void setCurrentCalories(CaloriesDTO currentCalories) {
        this.currentCalories = currentCalories;
    }

    public List<CaloriesDTO> getCaloriesDTOList() {
        return caloriesDTOList;
    }

    public void setCaloriesDTOList(List<CaloriesDTO> caloriesDTOList) {
        this.caloriesDTOList = caloriesDTOList;
    }
}
