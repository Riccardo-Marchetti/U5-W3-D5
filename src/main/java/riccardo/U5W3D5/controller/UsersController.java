package riccardo.U5W3D5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.U5W3D5.entities.Event;
import riccardo.U5W3D5.entities.Users;
import riccardo.U5W3D5.exceptions.BadRequestException;
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
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    public Page<Users> getAllUser (@RequestParam (defaultValue = "0") int page, @RequestParam (defaultValue = "10") int size, @RequestParam (defaultValue = "username") String sortBy){
        return usersService.getAllUser(page, size, sortBy);
    }

    @GetMapping ("/me")
    public Users getUser (@PathVariable Users currentUser){
        return currentUser;
    }

    @PutMapping ("/me")
    public Users updateProfile (@RequestBody @Validated UsersDTO body, @AuthenticationPrincipal Users currentUser ){
        return this.usersService.findUserAndUpdate( body, currentUser.getId());
    }

    @DeleteMapping ("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile (@AuthenticationPrincipal Users currentUser){
        this.usersService.deleteUser(currentUser.getId());
    }

    @GetMapping ("/{userId}")
    private Users getUserById (@PathVariable UUID userId){
        return usersService.getUserById(userId);
    }

    @PutMapping ("/{userId}")
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    public Users findUserAndUpdate (@PathVariable UUID userId, @RequestBody @Validated UsersDTO body, BindingResult validation ){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return usersService.findUserAndUpdate(body, userId);
    }
    @DeleteMapping ("/{userId}")
    @PreAuthorize("hasAuthority('EVENT_ORGANIZER')")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable UUID userId){
        this.usersService.deleteUser(userId);
    }
}
