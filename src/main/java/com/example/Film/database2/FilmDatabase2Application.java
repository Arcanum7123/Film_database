package com.example.Film.database2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class FilmDatabase2Application {

	@Autowired
	private ActorRepository actorRepo;
	private FilmRepository filmRepo;

	public FilmDatabase2Application(ActorRepository actorRepo, FilmRepository filmRepo){
		this.actorRepo = actorRepo;
		this.filmRepo = filmRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(FilmDatabase2Application.class, args);
	}

	@GetMapping("/allActors")
	public Iterable<actor> getAllActors(){
		return actorRepo.findAll();
	}

	@GetMapping("actor/{id}")
	public actor getActorByID(@PathVariable("id") int actorID){
		return actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor not found"));
	}

	@GetMapping("filmsWith/{firstName}/{lastName}")
	public Iterable<String> getFilmByActor(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        return filmRepo.findFilmsWithActor(firstName, lastName);
	}

	@GetMapping("{id}/starsIn")
	public Iterable<film> getFilmByActorID(@PathVariable("id") int actorID){
		actor myActor = new actor();
		myActor = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor not found"));
		return myActor.starsIn;
	}

	@PostMapping("add/actor/{firstName}/{lastName}")
	public void addActor(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		actor newActor = new actor();
		newActor.setLastName(lastName);
		newActor.setFirstName(firstName);
		actorRepo.save(newActor);
	}

	@PutMapping("update/actor/{id}/{newFirst}/{newLast}")
	public void updateActor(@PathVariable("id") int actorID, @PathVariable("newFirst") String newFirst, @PathVariable("newLast") String newLast){
		actor updatedActor = new actor(actorID, newFirst, newLast);
		actorRepo.save(updatedActor);
	}

	@DeleteMapping("delete/actor/{id}/{firstName}/{lastName}")
	public void deleteActor(@PathVariable("id") int actorID, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		actor inputDetails = new actor(actorID, firstName, lastName);
		actor checkDetails = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor not found"));
		if ((inputDetails.getActorID() == checkDetails.getActorID() && (inputDetails.getFirstName().equals(checkDetails.getFirstName())) &&
				(inputDetails.getLastName().equals(checkDetails.getLastName())))){
			System.out.println("Matched");
			actorRepo.delete(checkDetails);
		} else {
			System.out.println("Not matched");
		}
	}
}
