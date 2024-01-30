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
	private CategoryRepository categoryRepository;
	private FilmCategoryRepository filmCategoryRepository;

	public FilmDatabase2Application(ActorRepository actorRepo, FilmRepository filmRepo, CategoryRepository categoryRepository, FilmCategoryRepository
			filmCategoryRepository){
		this.actorRepo = actorRepo;
		this.filmRepo = filmRepo;
		this.categoryRepository = categoryRepository;
		this.filmCategoryRepository = filmCategoryRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FilmDatabase2Application.class, args);
	}

	//Add category to film
	@PostMapping("/add/{category}/categoryTo/{title}")
	public void addCategoryToFilm(@PathVariable("category") String category, @PathVariable("title") String title){
		int categoryID = categoryRepository.findCategoryID(category);
		int filmID = filmRepo.findFilmID(title);
		Film_category newCategory = new Film_category();
		newCategory.setCategoryID(categoryID);
		newCategory.setFilmID(filmID);
		filmCategoryRepository.save(newCategory);
	}

	//Remove category from film
	@DeleteMapping("/removeFrom/category/{category}/film/{title}")
	public void removeCategoryFromFilm(@PathVariable("category") String category, @PathVariable("title") String title){
		int categoryID = categoryRepository.findCategoryID(category);
		int filmID = filmRepo.findFilmID(title);
		Film_category toBeRemoved = new Film_category(filmID, categoryID);
		filmCategoryRepository.delete(toBeRemoved);
	}

	//Show all categories for a given film
	@GetMapping("/filmCategoriesFor/{title}")
	public Iterable<String> getFilmCategories(@PathVariable("title") String title){
		return categoryRepository.findFilmCategories(title);
	}

	//Show all films of a given category

	//Show list of all actors
	@GetMapping("/allActors")
	public Iterable<Actor> getAllActors(){
		return actorRepo.findAll();
	}

	//Find actor info for a given ID
	@GetMapping("/actor/{id}")
	public Actor getActorByID(@PathVariable("id") int actorID){
		return actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor not found"));
	}

	//Show list of films starring given actor
	@GetMapping("/filmsWith/{firstName}/{lastName}")
	public Iterable<String> getFilmByActor(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        return filmRepo.findFilmsWithActor(firstName, lastName);
	}

	//List of films starring actor by ID
	@GetMapping("/{id}/starsIn")
	public Iterable<Film> getFilmByActorID(@PathVariable("id") int actorID){
		Actor myActor = new Actor();
		myActor = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor not found"));
		return myActor.starsIn;
	}

	//Add a new actor to the DB
	@PostMapping("/add/actor/{firstName}/{lastName}")
	public void addActor(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		Actor newActor = new Actor();
		newActor.setLastName(lastName);
		newActor.setFirstName(firstName);
		actorRepo.save(newActor);
	}

	//Change actor's first and/or last names, selected by their ID
	@PutMapping("/update/actor/{id}/{newFirst}/{newLast}")
	public void updateActor(@PathVariable("id") int actorID, @PathVariable("newFirst") String newFirst, @PathVariable("newLast") String newLast){
		Actor updatedActor = new Actor(actorID, newFirst, newLast);
		actorRepo.save(updatedActor);
	}

	//Remove an actor from the DB
	@DeleteMapping("/delete/actor/{id}/{firstName}/{lastName}")
	public void deleteActor(@PathVariable("id") int actorID, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		Actor inputDetails = new Actor(actorID, firstName, lastName);
		Actor checkDetails = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor not found"));
		if ((inputDetails.getActorID() == checkDetails.getActorID() && (inputDetails.getFirstName().equals(checkDetails.getFirstName())) &&
				(inputDetails.getLastName().equals(checkDetails.getLastName())))){
			actorRepo.delete(checkDetails);
		}
	}
}
