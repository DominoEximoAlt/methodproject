package hu.unideb.method.methodproject.dto;

import hu.unideb.method.methodproject.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.sql.Date;

@Data
public class CaloriesDTO {

    private Long id;

    private int overall;

    private int caloriesFromDiet;

    private int caloriesFromExercise;

    private User user;



    private Date logDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOverall() {
        return overall;
    }

    public void setOverall(int overall) {
        this.overall = overall;
    }

    public int getCaloriesFromDiet() {
        return caloriesFromDiet;
    }

    public void setCaloriesFromDiet(int caloriesFromDiet) {
        this.caloriesFromDiet = caloriesFromDiet;
    }

    public int getCaloriesFromExercise() {
        return caloriesFromExercise;
    }

    public void setCaloriesFromExercise(int caloriesFromExercise) {
        this.caloriesFromExercise = caloriesFromExercise;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }
}
