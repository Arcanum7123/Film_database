package com.example.films.database2;

import io.cucumber.java.en.When;

public class FilmsRatedTest extends SpringIntegrationTest{
    private final String rating = "R";
    private Iterable<String> films;

    @When("^I call /rated/R$")
    public void callingRatedR(){
        executeGet("localhost:8080/home/rated/R");
    }

    /*@Then("I receive a list of all films rated R")
    public void userReceivesListOfFilms(){
        Assertions.assertTrue(); //How to get body of response to compare to expected
    }*/
}
