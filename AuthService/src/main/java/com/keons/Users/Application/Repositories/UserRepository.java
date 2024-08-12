package com.keons.Users.Application.Repositories;

import com.keons.Users.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByEmailLike(String email);

    List<User> findByUsernameLike(String nameSearch);
}
