package com.example.Film.database2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query(value = "SELECT title FROM film WHERE (film_id IN (SELECT film_id FROM film_actor WHERE (actor_id = (SELECT actor_id FROM actor WHERE " +
            "((first_name = :firstName) AND (last_name = :lastName))))));", nativeQuery = true)
    Iterable<String> findFilmsWithActor(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query(value = "SELECT film_id FROM film WHERE title = :title ;", nativeQuery = true)
    int findFilmID(@Param("title") String title);

    @Query(value = "SELECT title FROM film WHERE (film_id IN (SELECT film_id FROM film_category WHERE (category_id = (SELECT category_id FROM category " +
            "WHERE name = :category ))));", nativeQuery = true)
    Iterable<String> findFilmsInCategory(@Param("category") String category);
}
