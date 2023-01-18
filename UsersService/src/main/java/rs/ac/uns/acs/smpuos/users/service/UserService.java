package rs.ac.uns.acs.smpuos.users.service;



import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import rs.ac.uns.acs.smpuos.users.model.User;
import rs.ac.uns.acs.smpuos.users.repository.UsersRepository;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder encoder;

    public UserService(){

    }

    public Optional<User> findById(String id){

        return usersRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public void insert(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        usersRepository.insert(user);
    }



    @Override
    public User login(String email, String password) {
        Optional<User> userData = usersRepository.findByEmail(email);
        if (userData.isPresent()) {
            if (encoder.matches(password, userData.get().getPassword())) {
                return userData.get();
            }
        }
        else

            return null;

        return null;
    }

    @Override
    public User updateUser(User user) {
        return usersRepository.save(user);
    }
}