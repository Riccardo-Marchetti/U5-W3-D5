package riccardo.U5W3D5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardo.U5W3D5.entities.Users;
import riccardo.U5W3D5.exceptions.BadRequestException;
import riccardo.U5W3D5.payloads.UserLoginDTO;
import riccardo.U5W3D5.payloads.UserRespDTO;
import riccardo.U5W3D5.payloads.UsersDTO;
import riccardo.U5W3D5.services.AuthService;
import riccardo.U5W3D5.services.UsersService;

@RestController
@RequestMapping ("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private UsersService usersService;

    // ENDPOINT PER LOGIN
    @PostMapping("/login")
    public UserRespDTO login (@RequestBody UserLoginDTO payload){
        return new UserRespDTO(this.authService.authenticationUserAndGenerateToken(payload));
    }
    @PostMapping ("/register")
    @ResponseStatus(HttpStatus.CREATED)
    private Users saveUser (@RequestBody @Validated UsersDTO body, BindingResult validation){
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return this.usersService.saveUser(body);
    }

}
