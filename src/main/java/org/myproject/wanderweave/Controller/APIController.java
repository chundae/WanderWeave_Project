package org.myproject.wanderweave.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class APIController {

    @GetMapping("/getLocation")
    public String getLocation() {
        RestTemplate restTemplate = new RestTemplate();
        String flaskApiurl = "https://localhost:5000/api/location/Seoul";
        String result = restTemplate.getForObject(flaskApiurl, String.class);
        return result;
    }
}
