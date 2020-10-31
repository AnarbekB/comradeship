package ru.balmukanov.comradeship.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.balmukanov.comradeship.dto.UserDto;
import ru.balmukanov.comradeship.entity.User;
import ru.balmukanov.comradeship.repository.MessageRepository;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final MessageRepository messageRepository;

    @Value("${spring.profile.active}")
    private String profile;

    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", UserDto.fromEntity(user));
            data.put("messages", this.messageRepository.findAll());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(this.profile));
        return "index";
    }
}
