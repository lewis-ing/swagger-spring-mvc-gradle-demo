package com.sousonic.demo.swagger.controller;

import com.google.common.collect.Lists;
import com.sousonic.demo.swagger.dao.UserRepository;
import com.sousonic.demo.swagger.model.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ruiminglu on 14-8-11.
 */

@Controller
@Api(value = "user", position = 1)
public class UserController {

    private Logger LOG = LoggerFactory.getLogger(UserController.class);

    private static AtomicLong counter = new AtomicLong(1);
    private static Map<Long, User> users = new HashMap<Long, User>();

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @ApiOperation(value = "user.id ", notes = "get user by id")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<User> findById(
             @PathVariable Long id) {
        LOG.info("id : class : {} , value : {} " , id.getClass() ,id);
        User user = userRepository.findOne(id);
        if(user != null) {
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity add(@RequestBody User user) {
        User _user = userRepository.save(user);
        return new ResponseEntity<User>(user,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity update(@RequestBody User user) {
        User _user = userRepository.save(user);
        return new ResponseEntity<User>(_user, HttpStatus.NO_CONTENT);
    }

    static {
        User user = new User();
        user.setUid(counter.getAndIncrement());
        user.setAge(20);
        user.setName("sonic");
        users.put(user.getUid(),user);

        User user2 = new User();
        user2.setUid(counter.getAndIncrement());
        user2.setAge(22);
        user2.setName("tom");
        users.put(user2.getUid(),user2);
    }

}
