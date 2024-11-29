package com.example.demo.Service.Activity;

import org.springframework.stereotype.Service;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

@Service
public class ExerciseService {

    // here i am implementing the call to the external api:
    private static final String API_URL = "https://exercise-db-fitness-workout-gym.p.rapidapi.com/exercises/muscle/";
    private static final String API_KEY = "c82fa910bemsh5ede39737835b07p1bf816jsn52a57457493b";
    private static final String API_HOST = "exercise-db-fitness-workout-gym.p.rapidapi.com";

    public HttpResponse<String> getExercisesByMuscle(String muscle) throws UnirestException {
        // The url will be the API URL plus the muscle typed by my user:
        String url = API_URL + muscle;

        //Implementing Unirest to call the external API:
        return Unirest.get(url)
                .header("x-rapidapi-key", API_KEY)
                .header("x-rapidapi-host", API_HOST)
                .asString();
    }
}
