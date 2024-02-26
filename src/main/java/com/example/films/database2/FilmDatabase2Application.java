package com.example.films.database2;

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
	private CategoryRepository categoryRepo;
	private FilmCategoryRepository filmCategoryRepo;
	private LanguageRepository languageRepo;
	private FilmActorRepository filmActorRepo;

	public FilmDatabase2Application(ActorRepository actorRepo, FilmRepository filmRepo, CategoryRepository categoryRepo, FilmCategoryRepository
			filmCategoryRepo, LanguageRepository languageRepo, FilmActorRepository filmActorRepo){
		this.actorRepo = actorRepo;
		this.filmRepo = filmRepo;
		this.categoryRepo = categoryRepo;
		this.filmCategoryRepo = filmCategoryRepo;
		this.languageRepo = languageRepo;
		this.filmActorRepo = filmActorRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(FilmDatabase2Application.class, args);
	}

	//Add whole new film
	@PostMapping("/addNewFilm")
	public String addNewFilm(@RequestBody Film filmDetails){
		String rating = filmDetails.getRating();
      	return switch (rating) {
            case "G", "PG", "PG-13", "R", "NC-17" -> {
                filmRepo.save(filmDetails);
                yield "Film added successfully";
            }
            default -> "Please enter a valid rating and try again.";
        };
	}

	//Add actor to a film
	@PostMapping("/addActorToFilm/{title}")
	public String addActorToFilm(@RequestBody Actor names, @PathVariable("title") String title){
		Integer actorID = actorRepo.findIDByName(names.getFirstName(), names.getLastName());
		Integer filmID = filmRepo.findFilmID(title);
		if ((actorID != null) && (filmID != null)) {
			Film_actor newEntry = new Film_actor(filmID, actorID);
			filmActorRepo.save(newEntry);
			return "Actor added to film.";
		} else {
			return "Actor and/or film does not exist.";
		}
	}

	//Update a film rating
	@PutMapping("/updateRating/{title}/{rating}")
	public String updateFilmRating(@PathVariable("title") String title, @PathVariable("rating") String rating){
		int filmID = filmRepo.findFilmID(title);
		Film toUpdate = filmRepo.findById(filmID).orElseThrow(() -> new ResourceAccessException("Film not found"));
		return switch (rating) {
			case "G", "PG", "PG-13", "R", "NC-17" -> {
			toUpdate.setRating(rating);
			filmRepo.save(toUpdate);
			yield "Rating successfully updated";
			}
			default -> "That is not a valid rating.";
		};
	}

	//Show films of a rating
	@GetMapping("/rated/{rating}")
	public Iterable<String> getFilmsRated(@PathVariable("rating") String rating){
		return filmRepo.findFilmsRated(rating);
	}

	//Show rating of a film
	@GetMapping("/filmRating/{title}")
	public String getFilmRating(@PathVariable("title") String title){
		return filmRepo.findFilmsRating(title);
	}

	//Show film's language
	@GetMapping("/languagesOf/{title}")
	public Iterable<String> getLanguagesOfFilm(@PathVariable("title") String title){
		return languageRepo.findLanguagesOfFilm(title);
	}

	//Add a language to a film
	@PutMapping("/add/{language}/secondLanguageTo/{title}")
	public String addLanguageToFilm(@PathVariable("language") String language, @PathVariable("title") String title){
		Film toAddLanguageTo = new Film();
		int filmID = filmRepo.findFilmID(title);
		toAddLanguageTo = filmRepo.findById(filmID).orElseThrow(() -> new ResourceAccessException("Film not found"));
		int languageToAddID = languageRepo.getLanguageID(language);
		toAddLanguageTo.setOriginalLanguageID(languageToAddID);
		filmRepo.save(toAddLanguageTo);
		return title + " now also in " + language;
	}

	//Show all films in a given language
	@GetMapping("/filmsInLanguage/{language}")
	public Iterable<String> getFilmsInLanguage(@PathVariable("language") String language){
		int languageID = languageRepo.getLanguageID(language);
		return filmRepo.findFilmsInLanguage(languageID);
	}

	//Search for films released in year x
	@GetMapping ("/filmsReleasedIn/{year}")
	public Iterable<String[]> getFilmsReleasedIn(@PathVariable("year") int year){
		return filmRepo.findFilmsReleasedIn(year);
	}

	//Search for films released before year x
	@GetMapping ("/filmsReleasedBefore/{year}")
	public Iterable<String[]> getFilmsReleasedBefore(@PathVariable("year") int year){
		return filmRepo.findFilmsReleasedBefore(year);
	}

	//Search for films released after year x
	@GetMapping ("/filmsReleasedAfter/{year}")
	public Iterable<String[]> getFilmsReleasedAfter(@PathVariable("year") int year){
		return filmRepo.findFilmsReleasedAfter(year);
	}

	//Search for films by description
	@GetMapping("/searchInDescription/{searchTerm}")
	public Iterable<String[]> searchInDescription(@PathVariable("searchTerm") String searchTerm){
		searchTerm = "%" + searchTerm + "%";
		return filmRepo.findFilmsDescribed(searchTerm);
	}

	//Add category to film
	@PostMapping("/add/{category}/categoryTo/{title}")
	public String addCategoryToFilm(@PathVariable("category") String category, @PathVariable("title") String title){
		int categoryID = categoryRepo.findCategoryID(category);
		int filmID = filmRepo.findFilmID(title);
		Film_category newCategory = new Film_category();
		newCategory.setCategoryID(categoryID);
		newCategory.setFilmID(filmID);
		filmCategoryRepo.save(newCategory);
		return title + " now associated with " + category;
	}

	//Remove category from film
	@DeleteMapping("/removeFrom/category/{category}/film/{title}")
	public String removeCategoryFromFilm(@PathVariable("category") String category, @PathVariable("title") String title){
		int categoryID = categoryRepo.findCategoryID(category);
		int filmID = filmRepo.findFilmID(title);
		Film_category toBeRemoved = new Film_category(filmID, categoryID);
		filmCategoryRepo.delete(toBeRemoved);
		return title + " not longer associated with " + category;
	}

	//Show all categories for a given film
	@GetMapping("/filmCategoriesFor/{title}")
	public Iterable<String> getFilmCategories(@PathVariable("title") String title){
		return categoryRepo.findFilmCategories(title);
	}

	//Show all films of a given category
	@GetMapping("/filmsInCategory/{category}")
	public Iterable<String> getFilmsInCategory(@PathVariable("category") String category){
		return filmRepo.findFilmsInCategory(category);
	}

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
        Actor myActor = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor not found"));
		return myActor.starsIn;
	}

	//Add a new actor to the DB
	@PostMapping("/add/actor/{firstName}/{lastName}")
	public String addActor(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		Actor newActor = new Actor();
		newActor.setLastName(lastName.toUpperCase());
		newActor.setFirstName(firstName.toUpperCase());
		actorRepo.save(newActor);
		return "New actor successfully added.";
	}

	//Change actor's first and/or last names, selected by their ID
	@PutMapping("/update/actor/{id}/{newFirst}/{newLast}")
	public String updateActor(@PathVariable("id") int actorID, @PathVariable("newFirst") String newFirst, @PathVariable("newLast") String newLast){
		Actor updatedActor = new Actor(actorID, newFirst.toUpperCase(), newLast.toUpperCase());
		actorRepo.save(updatedActor);
		return "Actor name successfully updated.";
	}

	//Remove an actor from the DB
	@DeleteMapping("/delete/actor/{id}/{firstName}/{lastName}")
	public String deleteActor(@PathVariable("id") int actorID, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
		Actor inputDetails = new Actor(actorID, firstName, lastName);
		Actor checkDetails = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor not found"));
		if ((inputDetails.getActorID() == checkDetails.getActorID() && (inputDetails.getFirstName().equals(checkDetails.getFirstName())) &&
				(inputDetails.getLastName().equals(checkDetails.getLastName())))){
			actorRepo.delete(checkDetails);
			return "Actor successfully deleted.";
		} else {
			return "The provided details do not match.";
		}
	}
}
