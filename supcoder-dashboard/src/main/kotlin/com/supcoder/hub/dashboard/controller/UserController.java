package com.supcoder.hub.dashboard.controller;

import com.supcoder.hub.core.util.JsonResult;
import com.supcoder.hub.dashboard.auth.JwtUtil;
import com.supcoder.hub.db.domain.User;
import com.supcoder.hub.db.service.UserService;
import com.supcoder.hub.core.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/currentUser")
    public ResponseEntity<?> getCurrentUser(@RequestHeader(value = "Authorization", required = false) String authorization) {
        if (authorization == null) {
            return ResponseEntity.badRequest().body("Authorization header is missing");
        }
        String username = jwtUtil.extractUsername(authorization);
        if (username == null) {
            return ResponseEntity.badRequest().body("Invalid authorization token");
        }
        User user = userService.queryByUsername(username);
        return ResponseEntity.ok(ResultUtil.success(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JsonResult<User>> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(ResultUtil.success(userService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<JsonResult<String>> saveUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok(ResultUtil.success("User saved successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<JsonResult<String>> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok(ResultUtil.success("User deleted successfully"));
    }

    @DeleteMapping("username/{username}")
    public ResponseEntity<JsonResult<String>> deleteUserByUserName(@PathVariable String username) {
        userService.deleteByUserName(username);
        return ResponseEntity.ok(ResultUtil.success("User deleted successfully"));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<JsonResult<User>> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(ResultUtil.success(userService.queryByUsername(username)));
    }


    @GetMapping("/list")
    public ResponseEntity<JsonResult<List<User>>> getUserList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<User> userList = userService.querySelective(null, page, size, null, null);
        return ResponseEntity.ok(ResultUtil.success(userList));
    }

}
