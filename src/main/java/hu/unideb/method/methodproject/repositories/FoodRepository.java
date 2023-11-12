package hu.unideb.method.methodproject.repositories;

import hu.unideb.method.methodproject.entities.Food;
import hu.unideb.method.methodproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findFoodByUser(User user);
}
