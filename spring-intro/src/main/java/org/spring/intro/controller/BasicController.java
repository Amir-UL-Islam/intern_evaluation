package org.spring.intro.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.intro.exception.NotFountException;
import org.spring.intro.model.dto.MUserDTO;
import org.spring.intro.model.entity.MUser;
import org.spring.intro.model.mapper.MUserMapper;
import org.spring.intro.service.MUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BasicController {

    private final MUserService userService;
    private final MUserMapper userMapper;

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new MUserDTO());
        return "add_user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid @ModelAttribute("user") MUserDTO user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add_user";
        }
        MUser entity = userMapper.map(user);
        userService.save(entity);
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) throws NotFountException {
        MUser user = userService.findById(id);
        if (user == null) {
            throw new NotFountException("User not found by id: " + id);
        }
        model.addAttribute("user", user);
        return "update_user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid MUser user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update_user";
        }

        userService.save(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) throws NotFountException {
        MUser user = userService.findById(id);
        if (user == null) {
            throw new NotFountException("User not found by id: " + id);
        }
        userService.delete(user);
        return "redirect:/index";
    }

}
