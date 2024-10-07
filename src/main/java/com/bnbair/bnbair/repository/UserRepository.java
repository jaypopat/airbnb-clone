package com.bnbair.bnbair.repository;

import com.bnbair.bnbair.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findDistinctByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    List<User> findByEmail(@Param("email") String email);
}