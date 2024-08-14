package spring.boot.lap11.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.lap11.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUsersByUserId(Integer userId);

    User findUserByUsername(String username);
}
