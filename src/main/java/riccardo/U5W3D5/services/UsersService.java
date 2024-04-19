package riccardo.U5W3D5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardo.U5W3D5.entities.Users;
import riccardo.U5W3D5.exceptions.BadRequestException;
import riccardo.U5W3D5.exceptions.NotFoundException;
import riccardo.U5W3D5.payloads.UsersDTO;
import riccardo.U5W3D5.repositories.UsersDAO;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    @Autowired
    private UsersDAO usersDAO;

    public List<Users> getAllUser (){
        return usersDAO.findAll();
    }

    public Users getUserById (UUID userId){
        return usersDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public Users saveUser (UsersDTO body){
        this.usersDAO.findByEmail(body.email()).ifPresent(
                users -> {
                    throw new BadRequestException("l'email: " + body.email() + " è gia in uso");
                }
        );
        Users users = new Users(body.username(), body.name(), body.surname(), body.email(), body.password(), body.role());
        users.setAvatar("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        return this.usersDAO.save(users);
    }

    public Users findUserAndUpdate (UsersDTO body, UUID userId){
        Users users = this.usersDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
        users.setUsername(body.username());
        users.setName(body.name());
        users.setSurname(body.surname());
        users.setEmail(body.email());
        users.setPassword(body.password());
        users.setAvatar("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        return this.usersDAO.save(users);
    }

    public void deleteUser (UUID userId){
        Users users = this.usersDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
        this.usersDAO.delete(users);
    }
}
