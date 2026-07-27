package com.example.DBdemo.Repository;

import com.example.DBdemo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);

    List<User> findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(String username);


    @Query("""
            Select count(u) from User u
            """)
    Integer countUsers();

}
