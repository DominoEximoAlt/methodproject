package hu.unideb.method.methodproject.dto;

import hu.unideb.method.methodproject.enums.ExerciseEnum;
import lombok.Data;

@Data
public class ExerciseDto {

    private long id;
    private ExerciseEnum exercise;

    private int time;

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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
