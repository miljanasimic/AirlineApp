package rs.ac.uns.acs.smpuos.users.controller;

import jdk.jfr.DataAmount;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    @RequestMapping(value = "/user-info", method = RequestMethod.GET)
    public Optional<User> getUser(
            @RequestParam(name = "id", required = true) String id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        userService.insert(user);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLogDetails user){
        User user1 = userService.login(user.email, user.password);
        if (user1!=null){
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
