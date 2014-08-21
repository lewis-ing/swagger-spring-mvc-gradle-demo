package com.sousonic.demo.swagger.controller;

import com.sousonic.demo.swagger.model.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
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

    private static AtomicLong counter = new AtomicLong(1);
    private static Map<Long, User> users = new HashMap<Long, User>();

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public @ResponseBody List<User> getAll() {
        return new ArrayList(users.values());
    }

    @ApiOperation(value = "user.id ", notes = "get user by id")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<User> findById(
            @ApiParam(defaultValue = "1", allowableValues = "1,2,3,4,5")  @PathVariable Long id) {
        User user = users.get(id);
        if(user != null){
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<User>(user, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity add(@RequestBody User user) {
        user.setUid(counter.getAndIncrement());
        users.put(user.getUid(), user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity update(@RequestBody User user) {
        users.put(user.getUid(), user);
        return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
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
