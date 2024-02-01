package com.example.Film.database2;

import java.io.Serializable;

public class FilmActorID implements Serializable {
    private int filmID;
    private int actorID;

    public FilmActorID(){}

    public FilmActorID(int filmID, int actorID){
        this.filmID = filmID;
        this.actorID = actorID;
    }

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
}
