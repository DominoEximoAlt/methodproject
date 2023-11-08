package hu.unideb.method.methodproject.repositories;

import hu.unideb.method.methodproject.entities.Calories;
import hu.unideb.method.methodproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaloriesRepository extends JpaRepository<Calories, Long> {

    Calories findCaloriesByUser(User user);

}
