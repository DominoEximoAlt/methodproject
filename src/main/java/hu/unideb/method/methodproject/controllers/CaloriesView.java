package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.CaloriesDTO;
import hu.unideb.method.methodproject.dto.ExerciseDto;
import hu.unideb.method.methodproject.dto.FoodDTO;
import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.mapper.UserMapper;
import hu.unideb.method.methodproject.repositories.CaloriesRepository;
import hu.unideb.method.methodproject.services.CaloriesService;
import hu.unideb.method.methodproject.services.ExerciseService;
import hu.unideb.method.methodproject.services.UserService;
import jakarta.annotation.PostConstruct;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestScope
public class CaloriesView {

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    CaloriesService caloriesService;

    @Autowired
    CaloriesRepository caloriesRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    UserView userView;

    CaloriesDTO currentCalories;

    List<CaloriesDTO> caloriesDTOList;

    private LineChartModel lineModelForExercise;

    private LineChartModel lineModelForDiet;

    @PostConstruct
    public void init(){
        currentCalories = new CaloriesDTO();
        caloriesDTOList = new ArrayList<>();
        lineModelForExercise = new LineChartModel();
        lineModelForDiet = new LineChartModel();
        createLineModelForExercise(userView);
        createLineChartModelForDiet(userView);
    }

    /**
     * Adds caloric value to the currentCalories from exercise
     * @param exerciseDto the current user's latest exercise
     */
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

    /**
     * Adds caloric value to the currentCalories from diet
     * @param foodDTO the current user's latest diet
     */

    public void addFromFood(FoodDTO foodDTO){
        int caloricValue = 0;

        switch (foodDTO.getFoodEnum()){
            case BEEF :
                caloricValue = 2;
                break;
            case CHICKEN:
                caloricValue = 1;
                break;
            case PORK:
                caloricValue = 3;
                break;
            case VEGETABLES:
                caloricValue = 2;
                break;
            case FRUITS:
                caloricValue = 2;
                break;
            case GYRO:
                caloricValue = 12;
                break;
            case TACO:
                caloricValue = 10;
                break;
            case RICE:
                caloricValue = 4;
                break;
            case POTATOES:
                caloricValue = 5;
                break;

        }

        currentCalories.setCaloriesFromDiet(currentCalories.getCaloriesFromExercise() + (foodDTO.getWeight().intValue() * caloricValue));
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

    public LineChartModel getLineModelForExercise() {
        return lineModelForExercise;
    }

    public void setLineModelForExercise(LineChartModel lineModelForExercise) {
        this.lineModelForExercise = lineModelForExercise;
    }

    public LineChartModel getLineModelForDiet() {
        return lineModelForDiet;
    }

    public void setLineModelForDiet(LineChartModel lineModelForDiet) {
        this.lineModelForDiet = lineModelForDiet;
    }

    /**
     * saves the currentCAlories into the database
     * @param userDto the current User
     */
    public void saveCalories(UserDto userDto){
        User user = userMapper.userDtoToUser(userService.findUserByUserName(userDto.getUsername()));
        currentCalories.setUser(user);
        currentCalories.setLogDate(Date.valueOf(LocalDate.now()));
        caloriesService.calculateOverall(currentCalories);
        caloriesService.save(currentCalories);
    }


    /**
     * Creates the linechart model for the exercise page
     * @param userView
     */
    public void createLineModelForExercise(UserView userView) {
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Integer> caloriesBurned = new ArrayList<>();
        for(CaloriesDTO calories : caloriesService.findByUserName(userView.getCurrentUser().getUsername())){
            if(calories.getCaloriesFromExercise() > 0){
                caloriesBurned.add(calories.getCaloriesFromExercise());
            }
        }
        List<Object> values = new ArrayList<>();

        values.addAll(caloriesBurned);

        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Calories Burned");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        for( CaloriesDTO dataElement : caloriesService.findByUserName(userView.getCurrentUser().getUsername())){
            if (dataElement.getCaloriesFromExercise() > 0){
                labels.add(dataElement.getLogDate().toString());
            }
        }
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        options.setMaintainAspectRatio(false);


        lineModelForExercise.setOptions(options);
        lineModelForExercise.setData(data);
    }

    /**
     * Creates the linechart model for the diet page
     * @param userView
     */

    public void createLineChartModelForDiet(UserView userView) {
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Integer> caloriesConsumed = new ArrayList<>();
        for(CaloriesDTO calories : caloriesService.findByUserName(userView.getCurrentUser().getUsername())){
            if(calories.getCaloriesFromDiet() > 0){
                caloriesConsumed.add(calories.getCaloriesFromDiet());
            }
        }
        List<Object> values = new ArrayList<>();

        values.addAll(caloriesConsumed);

        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel("Calories Consumed");
        dataSet.setBorderColor("rgb(75, 192, 192)");
        dataSet.setTension(0.1);
        data.addChartDataSet(dataSet);

        List<String> labels = new ArrayList<>();
        for( CaloriesDTO dataElement : caloriesService.findByUserName(userView.getCurrentUser().getUsername())){
            if (dataElement.getCaloriesFromDiet() > 0){
                labels.add(dataElement.getLogDate().toString());
            }

        }
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        options.setMaintainAspectRatio(false);


        lineModelForDiet.setOptions(options);
        lineModelForDiet.setData(data);
    }

}
