package rs.ac.uns.acs.smpuos.users.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.smpuos.users.model.User;
import rs.ac.uns.acs.smpuos.users.service.IUserService;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordEncoder encoder;

    //pregled-profila
    @RequestMapping(value = "/user-info", method = RequestMethod.GET)
    public Optional<User> getUser(
            @RequestParam(name = "id", required = true) String id) {
        return userService.findById(id);
    }

    //registracija
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        userService.insert(user);
    }

    //prijavljivanje
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLogDetails user){
        User user1 = userService.login(user.email, user.password);
        if (user1!=null){
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    //izmena-profila
    @PutMapping("/users/update")
    public ResponseEntity<User> updateUser(@RequestParam("id") String id, @RequestBody User User) {
        Optional<User> UserData = userService.findById(id);
        if (UserData.isPresent()) {
            User user1 = UserData.get();
            if ((User.getFirstName())!=null) {
                user1.setFirstName(User.getFirstName());
            }
            if ((User.getLastName())!=null) {
                user1.setLastName(User.getLastName());
            }
            if ((User.getPhoneNumber())!=null) {
                user1.setPhoneNumber(User.getPhoneNumber());
            }
            if ((User.getEmail())!=null) {
                user1.setEmail(User.getEmail());
            }
            if ((User.getPassword())!=null) {
                user1.setPassword(encoder.encode(User.getPassword()));
            }
            return new ResponseEntity(userService.updateUser(user1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public static class UserLogDetails {

        private String email;
        private String password;

        public UserLogDetails(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
