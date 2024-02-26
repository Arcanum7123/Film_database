package com.example.films.database2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT name FROM category WHERE (category_id IN (SELECT category_id FROM film_category WHERE (film_id = (SELECT film_id FROM film WHERE title = " +
            ":title))));", nativeQuery = true)
    Iterable<String> findFilmCategories(@Param("title") String title);

    @Query(value = "SELECT category_id FROM category WHERE name = :category ;", nativeQuery = true)
    int findCategoryID(@Param("category") String category);
}
