package org.myproject.wanderweave.Controller;

import org.myproject.wanderweave.Domain.User;
import org.myproject.wanderweave.Service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;


    @GetMapping("/favorites")
    public String favorites(@SessionAttribute("user") Optional<User> optionalUser, Model model) {
        User user = optionalUser.orElse(null);
        if(user != null) {
            model.addAttribute("user", user);
            return "/favorites";
        }
        return "/login";
    }
}
