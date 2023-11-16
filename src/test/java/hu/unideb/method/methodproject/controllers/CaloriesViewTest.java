package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.CaloriesDTO;
import hu.unideb.method.methodproject.dto.ExerciseDto;
import hu.unideb.method.methodproject.dto.FoodDTO;
import hu.unideb.method.methodproject.enums.ExerciseEnum;
import hu.unideb.method.methodproject.enums.FoodEnum;
import hu.unideb.method.methodproject.mapper.UserMapper;
import hu.unideb.method.methodproject.repositories.CaloriesRepository;
import hu.unideb.method.methodproject.services.CaloriesService;
import hu.unideb.method.methodproject.services.ExerciseService;
import hu.unideb.method.methodproject.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CaloriesViewTest {

    @Mock
    ExerciseService exerciseService;

    @Mock
    CaloriesService caloriesService;

    @Mock
    CaloriesRepository caloriesRepository;

    @Mock
    UserMapper userMapper;

    @Mock
    UserService userService;

    @Mock
    UserView userView;

    @InjectMocks
    private CaloriesView underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        underTest = null;
    }


    @Test
    void testAddFromExcerciseShouldAddCaloriesProperly() {

        ExerciseDto exerciseDto = new ExerciseDto();
        exerciseDto.setTime(10.0);
        exerciseDto.setExercise(ExerciseEnum.WALKING);
        underTest.setCurrentCalories(new CaloriesDTO());

        underTest.addFromExcercise(exerciseDto);

        assertEquals(20,underTest.currentCalories.getCaloriesFromExercise());
    }

    @Test
    void testAddFromFoodShouldAddCaloriesProperly() {

        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setWeight(200.0);
        foodDTO.setFoodEnum(FoodEnum.GYRO);

        underTest.setCurrentCalories(new CaloriesDTO());

        underTest.addFromFood(foodDTO);

        assertEquals(12*foodDTO.getWeight(),underTest.currentCalories.getCaloriesFromDiet());
    }
}