package hu.unideb.method.methodproject.controllers;

import hu.unideb.method.methodproject.dto.FoodDTO;
import hu.unideb.method.methodproject.dto.UserDto;
import hu.unideb.method.methodproject.entities.Food;
import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.enums.ExerciseEnum;
import hu.unideb.method.methodproject.enums.FoodEnum;
import hu.unideb.method.methodproject.mapper.FoodMapper;
import hu.unideb.method.methodproject.mapper.UserMapper;
import hu.unideb.method.methodproject.services.FoodService;
import hu.unideb.method.methodproject.services.UserService;
import jakarta.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestScope
public class FoodView {

    @Autowired
    FoodService foodService;

    @Autowired
    FoodMapper foodMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    private Map<String, String> foods = new HashMap<>();

    private String food;

    private FoodDTO currentFood;

    private List<FoodDTO> foodDTOList;

    @PostConstruct
    public void init(){
        currentFood = new FoodDTO();
        foodDTOList = new ArrayList<>();
        foods.put("BEEF","BEEF");
        foods.put("CHICKEN","CHICKEN");
        foods.put("PORK","PORK");
        foods.put("VEGETABLES","VEGETABLES");
        foods.put("FRUITS","FRUITS");
        foods.put("GYRO","GYRO");
        foods.put("TACO","TACO");
        foods.put("RICE","RICE");
        foods.put("POTATOES","POTATOES");
    }

    public Map<String, String> getFoods() {
        return foods;
    }

    public void setFoods(Map<String, String> foods) {
        this.foods = foods;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public FoodDTO getCurrentFood() {
        if(food != null){
            currentFood.setFoodEnum(Enum.valueOf(FoodEnum.class,food));
        }
        return currentFood;
    }

    public void setCurrentFood(FoodDTO currentFood) {
        this.currentFood = currentFood;
    }

    public List<FoodDTO> getFoodDTOList() {
        return foodDTOList;
    }

    public void setFoodDTOList(List<FoodDTO> foodDTOList) {
        this.foodDTOList = foodDTOList;
    }

    /**
     * saves the current Diet to the curent user through database
     * @param userDto
     */
    public void addFood(@NotNull UserDto userDto){
        User user = userMapper.userDtoToUser(userService.findUserByUserName(userDto.getUsername()));
        currentFood.setUser(user);
        currentFood.setFoodEnum(Enum.valueOf(FoodEnum.class,food));
        foodService.save(currentFood);
        foodDTOList.add(currentFood);
    }
}
