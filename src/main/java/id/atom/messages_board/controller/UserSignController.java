package id.atom.messages_board.controller;

import id.atom.messages_board.dto.request.SignUpDTO;
import id.atom.messages_board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserSignController {
    private final UserService userService;

    @PostMapping("/register")
    public String getRegister(@Valid @RequestBody SignUpDTO signUpDTO) {
        userService.saveUser(signUpDTO);
        return "You have been registered!";
    }
}
