package com.j23.server.services.auth;

import com.j23.server.exception.UserNotFoundException;
import com.j23.server.models.Employee;
import com.j23.server.models.auth.Role;
import com.j23.server.models.auth.User;
import com.j23.server.repos.RoleRepo;
import com.j23.server.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Iterable<User> findAllUser() {
        return userRepo.findAll();
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User findUserById(Long id) {
        return userRepo.findById(String.valueOf(id)).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(String.valueOf(id));
    }

    public User registerNewUser(User user) {
//        Role role = roleRepo.findById("User").get();
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        user.setRole(roles);

        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
        return userRepo.save(user);
    }

    public void initRolesAndUser() {
//        create role
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleRepo.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("User role");
        roleRepo.save(userRole);

//        create user with role
        User adminUser = new User();
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        adminUser.setUsername("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userRepo.save(adminUser);

//        User user = new User();
//        user.setUserFirstName("jeremy");
//        user.setUserLastName("wib");
//        user.setUsername("jer123");
//        user.setUserPassword(getEncodedPassword("jer@pass"));
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userRepo.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder().encode(password);
    }
}
