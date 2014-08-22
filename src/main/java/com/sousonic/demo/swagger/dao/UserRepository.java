package com.sousonic.demo.swagger.dao;

import com.sousonic.demo.swagger.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ruiminglu on 14-8-22.
 */
public interface UserRepository extends CrudRepository<User,Long>{

    User findByName(String name);

    User findByUid(Long id);

    List<User> findByAge(Long age);

    //@Query("select u from User u where u.age > ? ")
    //List<User> findByLargeOfAge(Long age);
}
