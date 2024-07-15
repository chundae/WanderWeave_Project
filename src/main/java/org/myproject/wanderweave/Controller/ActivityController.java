package org.myproject.wanderweave.Controller;

import jakarta.servlet.http.HttpSession;
import org.myproject.wanderweave.Domain.Activity;
import org.myproject.wanderweave.Domain.Favorite;
import org.myproject.wanderweave.Domain.User;
import org.myproject.wanderweave.Service.ActivityService;
import org.myproject.wanderweave.Service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private FavoriteService favoriteService;

    //활동 추가 페이지
    @GetMapping("/activity/{TripId}/add")
    public String addActivity(@PathVariable("TripId") Optional<Long> optionalTripId, Model model,
                              @SessionAttribute("user") Optional<User> optionalUser) {
        User user = optionalUser.orElse(null);
        Long tripId = optionalTripId.orElse(null);
        if (tripId != null) {
            model.addAttribute("user", user);
            model.addAttribute("tripId", tripId);
            model.addAttribute("Activity", new Activity());
            return "/add_activity";
        }
        return "/login";
    }

    //활동 생성
    @PostMapping("/activities/create")
    public String createActivity(@SessionAttribute("user") Optional<User> optionalUser, Activity activity) {
        User user = optionalUser.orElse(null);
        Long tripId = activity.getTripId();
        if (user != null) {
            activityService.addActivity(activity);
            return "redirect:/trip/" + tripId + "/details"; // tripId를 URL에 추가
        }
        return "/login";
    }

    //활동 디테일페이지 이동
    @GetMapping("/activity/{activityId}/detail")
    public String detailActivity(@PathVariable("activityId") Optional<Long> opActivityId, Model model,
                                 @SessionAttribute("user") Optional<User> optionalUser) {
        User user = optionalUser.orElse(null);
        Long activityId = opActivityId.orElse(null);
        if (user != null) {
            Optional<Activity> viewActivity = activityService.viewActivity(activityId);
            if (viewActivity.isPresent()) {
                Activity activity = viewActivity.get();
                model.addAttribute("activity", activity);
                model.addAttribute("user", user);
                return "/activity_detail";
            }
        }
        return "/login";

    }

    //활동 수정페이지 이동
    @GetMapping("/activity/{ActivityId}/edit")
    public String editActivity(@SessionAttribute("user") Optional<User> optionalUser, Model model,
                               @PathVariable("ActivityId") Optional<Long> opActivityId, HttpSession session) {
        User user = optionalUser.orElse(null);
        Long activityId = opActivityId.orElse(null);
        if (user != null && activityId != null) {
            Optional<Activity> viewActivity = activityService.viewActivity(activityId);
            if (viewActivity.isPresent()) {
                Activity activity = viewActivity.get();
                model.addAttribute("act", activity);
                model.addAttribute("user", user);
                return "/edit_activity";
            }
        }
        return "/login";
    }

    //활동 수정
    @PostMapping("/activity/edit")
    public String editActivity(@SessionAttribute("user") Optional<User> optionalUser, Activity activity) {
        User user = optionalUser.orElse(null);
        if (user != null && activity != null) {
            Long activityId = activity.getActivityId();
            activityService.updateActivity(activity, activityId);
            return "redirect:/trip/" + activity.getTripId() + "/details";
        }
        return "/login";
    }

    //활동 삭제
    @GetMapping("/activity/{activityId}/delete")
    public String deleteActivity(@SessionAttribute("user") Optional<User> optionalUser,
                                 @PathVariable("activityId") Optional<Long> opActivityId, HttpSession session) {
        User user = optionalUser.orElse(null);
        Long activityId = opActivityId.orElse(null);
        if (user != null && activityId != null) {
            session.setAttribute("user", user);
            activityService.deleteActivity(activityId);
            return "redirect:/";
        }
        return "/login";
    }

//    //활동 즐겨찾기 추가
//    @GetMapping("/activity/{activityId}/Like")
//    public String LikeActivity(@SessionAttribute("user") Optional<User> optionalUser,
//                               @PathVariable("activityId") Optional<Long> opActId){
//        User user = optionalUser.orElse(null);
//        Long activityId = opActId.orElse(null);
//
//        if (user != null && activityId != null) {
//            Favorite favorite = new Favorite(activityId,user.getUserId());
//            favoriteService.addActivity(favorite);
//            return "redirect:/favorites";
//        }
//        return "/login";
//    }
}
