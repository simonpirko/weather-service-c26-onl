package by.tms.weatherservicec26onl.web.controller;

//import by.tms.weatherservicec26onl.auth.TokenProvider;
//import by.tms.weatherservicec26onl.dto.LoginUserDto;
import by.tms.weatherservicec26onl.dto.RegistrationUserDto;
import by.tms.weatherservicec26onl.entity.User;
import by.tms.weatherservicec26onl.mapper.UserMapper;
import by.tms.weatherservicec26onl.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    //private final TokenProvider tokenProvider;

    @PostMapping("/registration")
    public User registration(@RequestBody RegistrationUserDto dto) {
        return userService.add(userMapper.registrationUserDtoToUser(dto));
    }

    /**
     @PostMapping("/login")
    public String login(@RequestBody LoginUserDto dto) {
        User user = userService.findByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        return tokenProvider.generateToken(user);
    }
     */
}
