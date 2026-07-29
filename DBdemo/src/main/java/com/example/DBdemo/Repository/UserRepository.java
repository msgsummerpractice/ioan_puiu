package com.example.DBdemo.Repository;

import com.example.DBdemo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(long id);

    List<User> findTop10ByUsernameContainingIgnoreCaseOrderByUsernameAsc(String username);


    @Query("""
            Select count(u) from User u
            """)
    Integer countUsers();

}
