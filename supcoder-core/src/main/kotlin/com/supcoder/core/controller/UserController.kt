package com.supcoder.core.controller
import com.supcoder.core.model.User
import com.supcoder.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
/**
 * UserController
 *
 * @author lee
 * @date 2024/12/6
 */
@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping
    fun getAllUsers(): List<User> {
        return userService.findAllUsers()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userService.findUserById(id)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(@RequestBody user: User): User {
        return userService.saveUser(user)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): ResponseEntity<User> {
        val existingUser = userService.findUserById(id)
        return if (existingUser != null) {
            user.id = id
            ResponseEntity.ok(userService.saveUser(user))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        val user = userService.findUserById(id)
        return if (user != null) {
            userService.deleteUserById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}