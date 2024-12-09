package com.supcoder.hub.dashboard.controller


import com.supcoder.hub.core.util.JsonResult
import com.supcoder.hub.dashboard.model.vo.City
import com.supcoder.hub.dashboard.model.vo.CurrentUser
import com.supcoder.hub.dashboard.model.vo.Geographic
import com.supcoder.hub.dashboard.model.vo.Province
import com.supcoder.hub.dashboard.model.vo.Tag
import com.supcoder.hub.db.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.supcoder.hub.core.util.ResultUtil

@RestController
@RequestMapping("/api/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService


    @GetMapping("/currentUser")
    fun getCurrentUser(): ResponseEntity<JsonResult<CurrentUser>> {
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
        return ResponseEntity.ok(ResultUtil.success(currentUser)) as ResponseEntity<JsonResult<CurrentUser>>
    }

//    @GetMapping
//    fun findAllUsers(): ResponseEntity<JsonResult<List<User>>> {
//        return ResponseEntity.ok(ResultUtil.success(userService.findAll()))
//    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: Long): ResponseEntity<JsonResult<User>> {
        return ResponseEntity.ok(ResultUtil.success(userService.findById(id))) as ResponseEntity<JsonResult<User>>
    }

    @PostMapping
    fun saveUser(@RequestBody user: User): ResponseEntity<JsonResult<String>> {
        userService.add(user)
        return ResponseEntity.ok(ResultUtil.success("User saved successfully")) as ResponseEntity<JsonResult<String>>
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long): ResponseEntity<JsonResult<String>> {
        userService.deleteById(id)
        return ResponseEntity.ok(ResultUtil.success("User deleted successfully")) as ResponseEntity<JsonResult<String>>
    }

    @GetMapping("/by-username/{username}")
    fun findByUsername(@PathVariable username: String): ResponseEntity<JsonResult<User>> {
        return ResponseEntity.ok(ResultUtil.success(userService.queryByUsername(username))) as ResponseEntity<JsonResult<User>>
    }
}
