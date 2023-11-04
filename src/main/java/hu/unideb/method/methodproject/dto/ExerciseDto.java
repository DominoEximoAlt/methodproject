package hu.unideb.method.methodproject.dto;

import hu.unideb.method.methodproject.entities.User;
import hu.unideb.method.methodproject.enums.ExerciseEnum;
import lombok.Data;

@Data
public class ExerciseDto {

    private long id;
    private ExerciseEnum exercise;

    private Double time;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ExerciseEnum getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseEnum exercise) {
        this.exercise = exercise;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
