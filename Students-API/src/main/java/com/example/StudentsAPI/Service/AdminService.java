package com.example.StudentsAPI.Service;

import com.example.StudentsAPI.Model.Admin;
import com.example.StudentsAPI.Model.Student;
import com.example.StudentsAPI.Repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.StudentsAPI.Custom.Constants.NOT_FOUND_MSG;

@Service
@AllArgsConstructor
public class AdminService implements UserDetailsService {

    private AdminRepository adminRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsername(username);
        if(optionalAdmin.isEmpty()) {
            throw new UsernameNotFoundException(String.format(NOT_FOUND_MSG, "User", username));
        }
        Admin admin = optionalAdmin.get();
        UserDetails user = User
                .builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
        return user;
    }

    public void registerAdmin(Admin admin) {
        String encodedPassword = bCryptPasswordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        adminRepository.save(admin);
    }
}
