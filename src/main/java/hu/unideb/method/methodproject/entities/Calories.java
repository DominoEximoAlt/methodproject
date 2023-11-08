package hu.unideb.method.methodproject.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Calories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "OVERALL")
    private int overall;
    @Column(name = "FROMDIET")
    private int caloriesFromDiet;
    @Column(name = "FROMEXERCISE")
    private int caloriesFromExercise;

    @Column(name = "LOGDATE")
    private Date logDate;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
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
