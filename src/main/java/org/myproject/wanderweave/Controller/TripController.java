package org.myproject.wanderweave.Controller;

import org.myproject.wanderweave.Domain.Activity;
import org.myproject.wanderweave.Domain.Trip;
import org.myproject.wanderweave.Domain.User;
import org.myproject.wanderweave.Service.ActivityService;
import org.myproject.wanderweave.Service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import java.util.List;
import java.util.Optional;

@Controller
public class TripController {

    @Autowired
    private TripService tripService;

    @Autowired
    private ActivityService activityService;

    //trip 페이지 이동
    @GetMapping("/Trip")
    public String trip(@SessionAttribute("user") Optional<User> optionalUser, Model model) {
        User user = optionalUser.orElse(null);

        if (user != null) {
            List<Trip> viewList = tripService.viewAllTrips(user.getUserId());
            if (viewList != null) {
                System.out.println("페이지 로딩");
                model.addAttribute("user", user);
                model.addAttribute("viewList", viewList);
                return "/Trip";
            }
        }
        return "/Nologin_Trip";
    }

    //trip 생성페이지 이동
    @GetMapping("/add_trip")
    public String addTrip(@SessionAttribute("user") Optional<User> optionalUser, Model model) {
        User user = optionalUser.orElse(null);
        if(user != null) {
            model.addAttribute("user", user);
            model.addAttribute("Trip", new Trip());
            return "/add_trip";
        }
        return "/login";
    }

    //trip 생성
    @PostMapping("/trips/create")
    public String createTrip(@SessionAttribute("user") Optional<User> optionalUser, Model model, Trip trip) {
        User user = optionalUser.orElse(null);
        if(user != null) {
            tripService.addTrip(trip);
            return "redirect:/";
        }
        return "/login";
    }

    //trip 디테일페이지 이동
    @GetMapping("/trip/{TripId}/details")
    public String viewTrip(@SessionAttribute("user") Optional<User> optionalUser, Model model,
                           @PathVariable("TripId") Optional<Long> optionalTrip) {
        User user = optionalUser.orElse(null);
        Long tripId = optionalTrip.orElse(null);
        if(user != null && tripId != null) {
            Optional<Trip> trip = tripService.viewTrip(tripId);
            List<Activity> activityList = activityService.viewAllActivity(tripId);
            if( trip.isPresent()) {
                Trip tripDetail = trip.get();
                model.addAttribute("Trip", tripDetail);
                model.addAttribute("user", user);
                model.addAttribute("activityList", activityList);
                return "trip_detail";
            }
        }
        return "/login";
    }

    //trip 수정페이지 이동
    @GetMapping("/trip/{TripId}/edit")
    public String editTrip(@SessionAttribute("user") Optional<User> optionalUser, Model model,
                           @PathVariable("TripId") Optional<Long> optionalTripId) {
        User user = optionalUser.orElse(null);
        Long tripId = optionalTripId.orElse(null);
        if(user != null && tripId != null) {
            Optional<Trip> trip = tripService.viewTrip(tripId);
            if(trip.isPresent()) {
                Trip EditTrip = trip.get();
                model.addAttribute("Trip", EditTrip);
                model.addAttribute("user", user);
                return "/edit_trip";
            }
        }
        return "/login";
    }

    //trip 수정
    @PostMapping("/trips/edit")
    public String editTrip(@SessionAttribute("user") Optional<User> optionalUser, Model model,
                           Trip trip) {
        User user = optionalUser.orElse(null);
        Long tripId = trip.getTripId();
        if(user != null) {
            tripService.updateTrip(trip,tripId);
            return "redirect:/trip/" + tripId + "/details";
        }
        return "/login";
    }

    //trip 삭제
    @GetMapping("/delete/{TripId}/trip")
    public String delTrip(@SessionAttribute("user") Optional<User> optionalUser,
                          @PathVariable("TripId") Optional<Long> optionalTrip){
        User user = optionalUser.orElse(null);
        Long tripId = optionalTrip.orElse(null);
        if (user != null && tripId != null) {
            tripService.deleteTrip(tripId);
            return "redirect:/";
        }
        return "/login";
    }
}
