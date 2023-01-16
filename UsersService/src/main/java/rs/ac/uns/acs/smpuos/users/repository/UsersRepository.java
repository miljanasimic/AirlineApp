package rs.ac.uns.acs.smpuos.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.uns.acs.smpuos.users.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}
