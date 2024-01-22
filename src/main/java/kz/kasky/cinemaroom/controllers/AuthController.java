package kz.kasky.cinemaroom.controllers;


import jakarta.validation.Valid;
import kz.kasky.cinemaroom.models.dto.RegistrationDto;
import kz.kasky.cinemaroom.models.entities.UserEntity;
import kz.kasky.cinemaroom.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();

        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping("/register/save")
    public String getRegisterForm(@Valid @ModelAttribute("user") RegistrationDto user,
                                  BindingResult result, Model model) {

        UserEntity existingUser = userService.findByUsername(user.getUserName());


        if (existingUser != null && existingUser.getUsername() != null) {
            return "redirect:/register?fail";
        }



        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userService.saveUser(user);

        return "redirect:/movies?success";


    }


}
