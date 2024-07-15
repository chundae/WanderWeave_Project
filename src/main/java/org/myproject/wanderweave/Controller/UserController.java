package org.myproject.wanderweave.Controller;

import jakarta.servlet.http.HttpSession;
import org.myproject.wanderweave.Domain.Trip;
import org.myproject.wanderweave.Domain.User;
import org.myproject.wanderweave.Service.TripService;
import org.myproject.wanderweave.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TripService tripService;

    //로그인페이지
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //로그인성공시 메인페이지 이동
    @PostMapping("/login")
    public String login(@RequestParam("userEmail") String Email, @RequestParam("password") String password, HttpSession session) {
        Optional<User> user = userService.login(Email,password);
        if(user.isPresent()) {
            session.setAttribute("user", user.get());

            System.out.println("로그인성공" + user.get().getUserId());
            return "redirect:/Trip";
        }

        return "login";
    }

    //회원가입 페이지 이동
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user", new User());
        return "join";
    }

    //회원가입
    @PostMapping("/join")
    public String join(User user) {
        userService.save(user);
        return "redirect:/login";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //마이페이지
    @GetMapping("/mypage")
    public String profile(@SessionAttribute("user") Optional<User> optionalUser, Model model) {
        User user = optionalUser.orElse(null);
        if(user != null) {
            List<Trip> viewList = tripService.viewAllTrips(user.getUserId());
            if(viewList != null) {
                model.addAttribute("user", user);
                model.addAttribute("viewList", viewList);
                return "/mypage";
            }
        }
        return "/login";
    }

    //회원정보수정
    @GetMapping("/edit/{userId}/user")
    public String edit(@SessionAttribute("user") Optional<User> optionalUser
            ,@PathVariable("userId") Optional<Long> optionaluserId, Model model) {
        User user = optionalUser.orElse(null);
        Long userId = optionaluserId.orElse(null);
        if(user != null && userId != null) {
            model.addAttribute("user", user);
            return "/edit_user";
        }
        return "/login";
    }

    @PostMapping("/editUser")
    public String editUser(@SessionAttribute("user") Optional<User> optionalUser, User user) {
        User LoingUser = optionalUser.orElse(null);
        if(LoingUser != null) {
            Long userId = user.getUserId();
            userService.updateUser(user,userId);
            return "/mypage";
        }
        return "redirect:/login";
    }
}
