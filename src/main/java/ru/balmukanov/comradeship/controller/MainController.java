package ru.balmukanov.comradeship.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.balmukanov.comradeship.dto.UserDto;
import ru.balmukanov.comradeship.entity.User;
import ru.balmukanov.comradeship.service.MessageService;
import ru.balmukanov.comradeship.util.Views;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final MessageService messageService;

    private final ObjectMapper mapper;

    @Value("${spring.profile.active}")
    private String profile;

    private final ObjectWriter writer;

    public MainController(MessageService messageService, ObjectMapper mapper) {
        this.messageService = messageService;
        this.mapper = mapper;

        this.writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(Views.FullMessage.class);
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", UserDto.fromEntity(user));

            String messages = this.writer.writeValueAsString(this.messageService.all());
            model.addAttribute("messages", messages);
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(this.profile));
        return "index";
    }
}
