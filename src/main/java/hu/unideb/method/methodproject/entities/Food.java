package hu.unideb.method.methodproject.entities;

import hu.unideb.method.methodproject.enums.ExerciseEnum;
import hu.unideb.method.methodproject.enums.FoodEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Food{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "FOODTYPE")
    private FoodEnum foodEnum;

    @Column(name = "WEIGHT")
    private Double weight;

    @ManyToOne
    @JoinColumn(name = "username")
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
