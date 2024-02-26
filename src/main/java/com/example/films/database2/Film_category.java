package com.example.films.database2;

import jakarta.persistence.*;

@Entity
@Table(name = "film_category")
@IdClass(FilmCatID.class)
public class Film_category {
    @Id
    @Column(name = "film_id")
    private int filmID;

    @Id
    @Column(name = "category_id")
    private int categoryID;

    public Film_category(){}

    public Film_category(int filmID, int categoryID){
        this.filmID = filmID;
        this.categoryID = categoryID;
    }

    public int getFilmID() {
        return filmID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
