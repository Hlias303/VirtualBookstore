package com.example.VirtualBookstore.Service.Interface;

import com.example.VirtualBookstore.Model.User;

import java.util.List;

public interface RolesService {

    void SaveUser(User user) throws Exception;

    void DeleteUser(int id);

    List<User> ShowUsers();

    void SaveAdmin(User user);
}
