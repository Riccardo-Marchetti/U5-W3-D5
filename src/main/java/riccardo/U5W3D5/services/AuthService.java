package riccardo.U5W3D5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardo.U5W3D5.entities.Users;
import riccardo.U5W3D5.exceptions.UnauthorizedException;
import riccardo.U5W3D5.payloads.UserLoginDTO;
import riccardo.U5W3D5.security.JWTTools;

@Service
public class AuthService {

    @Autowired
    private UsersService usersService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticationUserAndGenerateToken (UserLoginDTO payload){
        // 1 controllo le credenziali
        // 1.1 cerco nel db tramite l’email l’utente
        Users users = this.usersService.findByEmail(payload.email());

        // 1.2 verifico se la password combacia con quella ricevuta nel payload
        if (users.getPassword().equals(payload.password())) {
            // 2 se tutto è ok, genero un token e lo torno
            return jwtTools.createToken(users);
        } else {
            // 3 se le credenziali non fossero ok —> 401 (Unauthorized)
            throw new UnauthorizedException("Credenziali errate! effettua di nuovo il login");
        }
    }
}
