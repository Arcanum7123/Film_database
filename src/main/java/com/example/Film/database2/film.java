package com.example.Film.database2;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "film")
public class film {
    @Id
    @Column(name = "film_ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int filmID;

    @ManyToMany
    @JoinTable(
        name = "film_actor",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id"))
    Set<actor> features;

    public film(){};

    public film(int filmID, String title, String description, int releaseYear){
        this.filmID=filmID;
        this.title=title;
        this.description=description;
        this.releaseYear=releaseYear;
    }

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private int releaseYear;

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
