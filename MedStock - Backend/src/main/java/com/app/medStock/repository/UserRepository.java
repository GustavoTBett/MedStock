package com.app.medStock.repository;

import com.app.medStock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, CustomQuerydslPredicateExecutor<User> {

}
