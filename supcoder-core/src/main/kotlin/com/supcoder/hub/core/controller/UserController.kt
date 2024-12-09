package com.supcoder.core.controller

import com.supcoder.core.model.vo.City
import com.supcoder.core.model.vo.CurrentUser
import com.supcoder.core.model.vo.Geographic
import com.supcoder.core.model.vo.Province
import com.supcoder.core.model.vo.Tag
import com.supcoder.core.model.domain.User
import com.supcoder.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService


    @GetMapping("/currentUser")
    fun getCurrentUser(): ResponseEntity<CurrentUser> {
        // 实现获取当前用户的逻辑
        val currentUser = CurrentUser(
            name = "John Doe",
            avatar = "http://example.com/avatar.jpg",
            userid = "12345",
            email = "john.doe@example.com",
            signature = "Hello, world!",
            title = "Developer",
            group = "Engineering",
            tags = listOf(Tag("dev", "Developer")),
            notifyCount = 5,
            unreadCount = 3,
            country = "China",
            access = "admin",
            geographic = Geographic(
                province = Province(label = "Guangdong", key = "gd"),
                city = City(label = "Shenzhen", key = "sz")
            ),
            address = "123 Main St",
            phone = "1234567890"
        )
        return ResponseEntity.ok(currentUser)
    }

    @GetMapping
    fun findAllUsers(): List<User> {
        return userService.findAllUsers()
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: Long): User? {
        return userService.findUserById(id)
    }

    @PostMapping
    fun saveUser(@RequestBody user: User): User {
        return userService.saveUser(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long) {
        userService.deleteUserById(id)
    }

    @GetMapping("/by-username/{username}")
    fun findByUsername(@PathVariable username: String): User? {
        return userService.findByUsername(username)
    }
}
