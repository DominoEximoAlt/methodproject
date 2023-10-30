package hu.unideb.method.methodproject.repositories;

import hu.unideb.method.methodproject.entities.Profile;
import hu.unideb.method.methodproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {

    Profile findProfileByUser(User user);
}
