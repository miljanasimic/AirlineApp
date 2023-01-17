package rs.ac.uns.acs.smpuos.users.service;

import rs.ac.uns.acs.smpuos.users.model.User;

import java.util.Optional;

public interface IUserService{
    Optional<User> findById(String id);
    void insert(User user);

    Optional<User> findByEmail(String email);

    User login(String email, String password);

    User updateUser (User user);

}
