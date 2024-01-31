package com.example.Film.database2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    @Query(value = "SELECT language_id FROM language WHERE name = :language ;", nativeQuery = true)
    int getLanguageID(@Param("language") String language);
}