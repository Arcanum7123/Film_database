package com.example.Film.database2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FilmActorRepository extends JpaRepository<Film_actor, Integer> {
    @Query(value = "SELECT actor_id FROM film_actor WHERE((actor_id = :actorID ) AND (film_id = :filmID ));", nativeQuery = true)
    int entryChecker(@Param("actorID") int actorID, @Param("filmID") int filmID);
}
