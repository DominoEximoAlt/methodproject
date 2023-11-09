package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.CaloriesDTO;
import hu.unideb.method.methodproject.dto.ExerciseDto;
import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.enums.ExerciseEnum;
import hu.unideb.method.methodproject.mapper.UserMapper;
import hu.unideb.method.methodproject.repositories.CaloriesRepository;
import hu.unideb.method.methodproject.services.CaloriesService;
import hu.unideb.method.methodproject.services.ExerciseService;
import hu.unideb.method.methodproject.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

    private LineChartModel lineModel;

    @PostConstruct
    public void init(){
        currentCalories = new CaloriesDTO();
        caloriesDTOList = new ArrayList<>();
        lineModel = new LineChartModel();
        createLineModel(userView);
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

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(LineChartModel lineModel) {
        this.lineModel = lineModel;
    }

    public void saveCalories(UserDto userDto){
        User user = userMapper.userDtoToUser(userService.findUserByUserName(userDto.getUsername()));
        currentCalories.setUser(user);
        currentCalories.setLogDate(Date.valueOf(LocalDate.now()));
        caloriesService.calculateOverall(currentCalories);
        caloriesService.save(currentCalories);
    }


    public void createLineModel(UserView userView) {
        ChartData data = new ChartData();

        LineChartDataSet dataSet = new LineChartDataSet();
        List<Integer> caloriesBurned = new ArrayList<>();
        for(CaloriesDTO calories : caloriesService.findByUserName(userView.getCurrentUser().getUsername())){
            caloriesBurned.add(calories.getCaloriesFromExercise());
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
            labels.add(dataElement.getLogDate().toString());
        }
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        options.setMaintainAspectRatio(false);


        lineModel.setOptions(options);
        lineModel.setData(data);
    }

}
