package com.example.films.database2;

import jakarta.persistence.*;

@Entity
@Table(name = "film_actor")
@IdClass(FilmActorID.class)
public class Film_actor {
    @Id
    @Column(name = "film_id")
    private int filmID;

    @Id
    @Column(name = "actor_id")
    private int actorID;

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public Film_actor(){}

    public Film_actor(int filmID, int actorID){
        this.filmID = filmID;
        this.actorID = actorID;
    }
}
