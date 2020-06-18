package com.example.demo.repository;
import com.example.demo.models.Usermodel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Usermodel,Long> {

    Usermodel findByUsername(String username);
}
