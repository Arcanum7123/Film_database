package com.example.Film.database2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<actor, Integer> {
    /*default actor getActorByName(String firstName, String lastName){
        actor match = new actor();

        return match;
    }*/
}
