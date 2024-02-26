package com.example.films.database2;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class NewActorStepDefinitions {
    private final String expectedFirstName = "BAROLD";
    private final String expectedLastName = "GIBBONS";
    Actor actor;

    @Given("a first and last name are supplied")
    public void makeEmptyActor(){
        actor = new Actor();
    }

    @When("I make a new actor")
    public void createActor(){
        actor.setFirstName(expectedFirstName);
        actor.setLastName(expectedLastName);
    }

    @Then("the actor should have all fields")
    public void checkFields(){
        Assertions.assertEquals(expectedFirstName, actor.getFirstName());
        Assertions.assertEquals(expectedLastName, actor.getLastName());
    }
}
