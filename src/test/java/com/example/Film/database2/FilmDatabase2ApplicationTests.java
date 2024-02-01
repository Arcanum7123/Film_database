package com.example.Film.database2;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(controllers = {FilmDatabase2Application.class})
class FilmDatabase2ApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ActorRepository actorRepo;
	@MockBean
	private FilmRepository filmRepo;
	@MockBean
	private CategoryRepository categoryRepo;
	@MockBean
	private FilmCategoryRepository filmCatRepo;
	@MockBean
	private LanguageRepository languageRepo;
	@MockBean
	private FilmActorRepository filmActorRepo;


	Actor barry = new Actor(18, "BAROLD", "GIBBONS");
	@Test
	public void testFindActorByID() throws Exception {
		when(actorRepo.findById(barry.getActorID())).thenReturn(Optional.of(barry));
		this.mockMvc.perform(get("/home/actor/18").accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.actorID").value(18))
				.andExpect(jsonPath("$.firstName").value("BAROLD")).andExpect(jsonPath("$.lastName").value("GIBBONS"));
	}

}
