package com.example.VirtualBookstore.Service.Implementation;

import com.example.VirtualBookstore.Model.User;
import com.example.VirtualBookstore.Repo.RolesRepo;
import com.example.VirtualBookstore.Repo.UserRepo;
import com.example.VirtualBookstore.Service.Interface.RolesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {

    private final RolesRepo roles_repo;
    private final UserRepo user_repo;

    public RolesServiceImpl(RolesRepo roles_repo, UserRepo user_repo) {
        this.roles_repo = roles_repo;
        this.user_repo = user_repo;
    }

    @Override
    public void SaveUser(User user) throws Exception {
        user.setPassword(user.getPassword());
        user.setUsername(user.getUsername());

        if (user_repo.existsByusername(user.getUsername())) {
            throw new Exception("Username Already Exists");
        } else {
            user.setRoles(roles_repo.findByName("USER"));
            user_repo.save(user);
        }
    }

    @Override
    public void DeleteUser(int id) {
        user_repo.deleteById(id);
    }

    @Override
    public List<User> ShowUsers() {
        return user_repo.findAll();
    }

    @Override
    public void SaveAdmin(User user) {
        user.setPassword(user.getPassword());
        user.setUsername(user.getUsername());
        user.setRoles(roles_repo.findByName("ADMIN"));
        user_repo.save(user);
    }
}
