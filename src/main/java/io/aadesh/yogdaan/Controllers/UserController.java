package io.aadesh.yogdaan.Controllers;

import io.aadesh.yogdaan.entity.AppUser;
import io.aadesh.yogdaan.service.UserService;
import io.aadesh.yogdaan.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private Utility util;

    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/getPreRegistration")
    public String getPreRegistration() {
        return "pre-registration";
    }


    @GetMapping("/getRegistration/{type}")
    public String getRegistrationPage(Model model, @PathVariable String type){
        AppUser appUser = userService.getAppUser(util.getUserId());
        if(appUser !=null){
            model.addAttribute("user",appUser);
        }
        model.addAttribute("type",type);
        return "register";
    }
    @PostMapping("/register")
    public String register(@RequestBody MultiValueMap<String,String> formData){

        AppUser appUser =new AppUser();
        appUser.setUserId(util.getUserId());
        appUser.setAddress(formData.getFirst("address"));
        appUser.setCity(formData.getFirst("city"));
        appUser.setEmail(formData.getFirst("email"));
        appUser.setName(formData.getFirst("name"));
        appUser.setManagerName(formData.getFirst("managerName"));
        appUser.setNgoType(formData.getFirst("ngoType"));
        appUser.setState(formData.getFirst("state"));
        appUser.setWebsite(formData.getFirst("website"));
        appUser.setPhoneNumber(formData.getFirst("phoneNumber"));
        appUser.setRegistrationNumber(formData.getFirst("registrationNumber"));

        userService.saveUser(appUser);

        return "home";
    }


}
