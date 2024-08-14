package spring.boot.lap11.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.lap11.ApiResponse.ApiException;
import spring.boot.lap11.Model.User;
import spring.boot.lap11.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
    public void updateUser(User user,Integer id) {
        User u = userRepository.findUsersByUserId(id);
        if (u == null){
            throw new ApiException("User not found");
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRegistrationDate(user.getRegistrationDate());

        userRepository.save(u);
    }
    public void deleteUser(Integer id) {
        User u = userRepository.findUsersByUserId(id);
        if (u == null){
            throw new ApiException("User not found");
        }
        userRepository.delete(u);
    }
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
