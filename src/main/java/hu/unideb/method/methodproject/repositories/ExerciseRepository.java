package hu.unideb.method.methodproject.repositories;

import hu.unideb.method.methodproject.entities.Exercise;
import hu.unideb.method.methodproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

    Exercise findExerciseByUser(User user);

}
