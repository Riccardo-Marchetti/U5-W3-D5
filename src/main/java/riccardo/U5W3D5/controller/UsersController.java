package riccardo.U5W3D5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardo.U5W3D5.entities.Event;
import riccardo.U5W3D5.entities.Users;
import riccardo.U5W3D5.payloads.EventDTO;
import riccardo.U5W3D5.payloads.UsersDTO;
import riccardo.U5W3D5.repositories.UsersDAO;
import riccardo.U5W3D5.services.UsersService;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping ("/user")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    private List<Users> getAllUser (){
        return usersService.getAllUser();
    }

    @GetMapping ("/{userId}")
    private Users getUserById (@PathVariable UUID userId){
        return usersService.getUserById(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Users saveUser (@RequestBody UsersDTO body){
        return usersService.saveUser(body);
    }

    @PutMapping ("/{userId}")
    private Users findUserAndUpdate (@PathVariable UUID userId, @RequestBody UsersDTO body ){
        return usersService.findUserAndUpdate(body, userId);
    }
    @DeleteMapping ("/{userId}")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    private void deleteUser (@PathVariable UUID userId){
        this.usersService.deleteUser(userId);
    }
}
