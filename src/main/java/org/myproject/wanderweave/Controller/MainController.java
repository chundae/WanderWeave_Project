package org.myproject.wanderweave.Controller;

import org.myproject.wanderweave.Domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index(@SessionAttribute("user") Optional<User> optionalUser) {
        User user = optionalUser.orElse(null);
        //로그인 상태 확인
        if (user == null) {
            return "/Nologin_Trip";
        } else {
            return "redirect:/Trip";
        }

    }


}
