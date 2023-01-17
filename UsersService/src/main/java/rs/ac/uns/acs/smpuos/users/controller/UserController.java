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
@RequestMapping("/user")
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
    public String addUser(@RequestBody User user) {
        String result = null;
        Optional<User> userData = userService.findByEmail(user.getEmail());
        if (userData.isPresent()) {
            result = "Nalog za email: " + user.getEmail() + " postoji.";
        } else {
            userService.insert(user);
            result = "Korisnik uspesno tegistrovan.";
        }
        return result;
    }

    //prijavljivanje
    @PostMapping("/login")
    public ResponseEntity<UserLoginResult> login(@RequestBody UserLogDetails user){
        User user1 = userService.login(user.email, user.password);

        if (user1!=null){
            UserLoginResult user2 = new UserLoginResult(user1.getId(), user1.getEmail());
            return new ResponseEntity<>(user2, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
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

    public static class UserLoginResult {
        private String id;
        private String email;

        public UserLoginResult(String id, String email) {
            this.id = id;
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
