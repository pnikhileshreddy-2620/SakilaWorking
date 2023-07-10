package com.cg.sakila.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cg.sakila.Exception.ActorDataException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.sakila.Exception.ActorDataException;
import com.cg.sakila.entity.Actor;
import com.cg.sakila.entity.Film;
import com.cg.sakila.service.ActorService;
import com.cg.sakila.service.FilmService;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    private ActorService actorService;
    private FilmService filmService;

    @Autowired
    public void setActorService(ActorService actorService) {
        this.actorService = actorService;
    }

    @Autowired
    public void setFilmService(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping(value = "/post")
    public ResponseEntity<String> createActor(@RequestBody Actor actor) {
        try {
            actorService.addActor(actor);
            return ResponseEntity.ok("Record Created Successfully");
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (ActorDataException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create record");
        }
    }

    @GetMapping("/lastname/{ln}")
    public ResponseEntity<Collection<Actor>> searchActorsByLastName(@PathVariable String ln) {
        Collection<Actor> actors = actorService.findActorsByLastName(ln);
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/firstname/{fn}")
    public ResponseEntity<Collection<Actor>> searchActorsByFirstName(@PathVariable String fn) {
        Collection<Actor> actors = actorService.findActorsByFirstName(fn);
        return ResponseEntity.ok(actors);
    }

    @PutMapping("/update/lastname/{id}")
    public ResponseEntity<?> updateActorLastName(@PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) throws ActorDataException {
        String newLastname = requestBody.get("newLastname");
        if (newLastname == null) {
            return ResponseEntity.badRequest().body("New Last name required");
        }
        Actor updatedActor = actorService.updateActorLastName(id, newLastname);
		if (updatedActor != null) {
		    return ResponseEntity.status(HttpStatus.OK).body("Last Name updated successfully");
		} else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found");
		}
    }

    @PutMapping("/update/firstname/{id}")
    public ResponseEntity<?> updateActorFirstName(@PathVariable("id") Short id,
            @RequestBody Map<String, String> requestBody) throws ActorDataException {
        String newFirstname = requestBody.get("newFirstname");
        if (newFirstname == null) {
            return ResponseEntity.badRequest().body("New First name required");
        }
        Actor updatedActor = actorService.updateActorFirstName(id, newFirstname);
		if (updatedActor != null) {
		    return ResponseEntity.status(HttpStatus.OK).body("First name updated successfully");
		} else {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Actor not found");
		}
    }

    @GetMapping("/toptenbyfilmcount")
    public ResponseEntity<List<Actor>> getTopTenActorsByFilmCount() {
        List<Actor> actors = actorService.getTopTenActorsByFilmCount();
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/{id}/films")
    public ResponseEntity<List<Film>> getFilmsByActorId(@PathVariable("id") Short actorId) {
        List<Film> films = actorService.getFilmsByActorId(actorId);
        if (films.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(films);
    }

    // Exception handling for ActorDataException
    @ExceptionHandler(ActorDataException.class)
    public ResponseEntity<String> handleActorDataException(ActorDataException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
