package com.pluralsight.controller;

import com.pluralsight.model.Ride;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestControllerTest {

    @Test(timeout = 3000)
    public void testGetRides() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
                "http://localhost:8080/ride_tracker/rides", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Ride>>() {
                });
        List<Ride> rides = ridesResponse.getBody();

        for (Ride ride : rides) {
            System.out.println("Ride name: " + ride.getName());
        }
    }

    @Test
    public void testCreateRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setName("Sagebrush Trail Ride");
        ride.setDuration(55);

        ride = restTemplate.postForObject("http://localhost:8080/ride_tracker/rides", ride, Ride.class);

    }

    @Test
    public void testGetRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
        System.out.println("Ride name: " + ride.getName());
    }

    @Test
    public void testUpdateRide() {
        RestTemplate restTemplate = new RestTemplate();

        String getUrl = "http://localhost:8080/ride_tracker/ride/1";
        String updateUrl = "http://localhost:8080/ride_tracker/ride";
        Ride ride = restTemplate.getForObject(getUrl, Ride.class);

        ride.setDuration(ride.getDuration() + 1);

        restTemplate.put(updateUrl, ride);
    }


    @Test
    public void testDelete() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete("http://localhost:8080/ride_tracker/delete/7");

    }


    @Test
    public void testBatchUpdate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject("http://localhost:8080/ride_tracker/batch", Ride.class);

    }


    @Test
    public void testException() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject("http://localhost:8080/ride_tracker/test", Ride.class);

    }
}
