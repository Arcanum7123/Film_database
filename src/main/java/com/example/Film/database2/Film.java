package com.example.Film.database2;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "film_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmID;

    @ManyToMany
    @JoinTable(
        name = "film_actor",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id"))
    Set<Actor> features;

    @ManyToMany(mappedBy = "hasFilms")
    Set<Category> inCategories;

    public Film(){}

    public Film(int filmID, String title, String description, int releaseYear, int languageID, Integer originalLanguageID, String rating){
        this.filmID=filmID;
        this.title=title;
        this.description=description;
        this.releaseYear=releaseYear;
        this.languageID=languageID;
        this.originalLanguageID=originalLanguageID;
        this.rating=rating;
    }

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private int releaseYear;

    @Column(name="language_id")
    private int languageID;

    @Column(name="original_language_id", nullable = true)
    private Integer originalLanguageID;

    @Column(name = "rating")
    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public int getOriginalLanguageID() {
        return originalLanguageID;
    }

    public void setOriginalLanguageID(Integer originalLanguageID) {
        this.originalLanguageID = originalLanguageID;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
