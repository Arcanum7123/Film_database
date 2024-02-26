package com.example.films.database2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
    @Query(value = "SELECT actor_id FROM actor WHERE ((first_name = :firstName ) && (last_name = :lastName ));", nativeQuery = true)
    Integer findIDByName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
