package rs.ac.uns.acs.smpuos.users.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.smpuos.users.model.User;

import java.util.Optional;

@Lazy
@Service
public interface IUserService{
    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    void insert(User user);

    User login(String email, String password);

    User updateUser (User user);

}
