package com.example.Film.database2;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="actor")
public class actor {
    @Id
    @Column(name="actor_id", unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorID;

    @ManyToMany(mappedBy = "features")
    Set<film> starsIn;

    public actor(){
    }

    public actor(int actorID, String firstName, String lastName) {
        this.actorID = actorID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
