Feature: Add new actor test
    AS A user
    I WANT to be able to add a new actor to the database
    SO THAT I keep the database up to date

    Scenario: Adding a new actor
        Given a first and last name are supplied
        When I make a new actor
        Then the actor should have all fields