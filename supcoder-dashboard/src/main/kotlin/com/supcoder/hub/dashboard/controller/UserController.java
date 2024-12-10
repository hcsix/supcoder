package com.supcoder.hub.dashboard.controller;

import com.supcoder.hub.core.util.JsonResult;
import com.supcoder.hub.dashboard.model.vo.City;
import com.supcoder.hub.dashboard.model.vo.CurrentUser;
import com.supcoder.hub.dashboard.model.vo.Geographic;
import com.supcoder.hub.dashboard.model.vo.Province;
import com.supcoder.hub.dashboard.model.vo.Tag;
import com.supcoder.hub.db.domain.User;
import com.supcoder.hub.db.service.UserService;
import com.supcoder.hub.core.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/currentUser")
    public ResponseEntity<JsonResult<CurrentUser>> getCurrentUser() {
        // 实现获取当前用户的逻辑
        CurrentUser currentUser = new CurrentUser(
            "John Doe",
            "http://example.com/avatar.jpg",
            "12345",
            "john.doe@example.com",
            "Hello, world!",
            "Developer",
            "Engineering",
            Arrays.asList(new Tag("dev", "Developer")),
            5,
            3,
            "China",
            "admin",
            new Geographic(
                new Province("Guangdong", "gd"),
                new City("Shenzhen", "sz")
            ),
            "123 Main St",
            "1234567890"
        );
        return ResponseEntity.ok(ResultUtil.success(currentUser));
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

    @GetMapping("/by-username/{username}")
    public ResponseEntity<JsonResult<User>> findByUsername(@PathVariable String username) {
        return ResponseEntity.ok(ResultUtil.success(userService.queryByUsername(username)));
    }
}
