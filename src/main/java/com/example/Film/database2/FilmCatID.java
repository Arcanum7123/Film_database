package com.example.Film.database2;

import java.io.Serializable;

public class FilmCatID implements Serializable {
    private int filmID;
    private int categoryID;

    public FilmCatID(){}

    public FilmCatID(int filmID, int categoryID){
        this.filmID = filmID;
        this.categoryID = categoryID;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
