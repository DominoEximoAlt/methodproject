package hu.unideb.method.methodproject.dto;

import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.enums.FoodEnum;
import jakarta.persistence.*;

public class FoodDTO {

    private Long id;

    private FoodEnum foodEnum;

    private Double weight;

    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FoodEnum getFoodEnum() {
        return foodEnum;
    }

    public void setFoodEnum(FoodEnum foodEnum) {
        this.foodEnum = foodEnum;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
