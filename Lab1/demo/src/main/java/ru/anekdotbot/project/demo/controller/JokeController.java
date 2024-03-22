package ru.anekdotbot.project.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.anekdotbot.project.demo.model.JokeData;
import ru.anekdotbot.project.demo.repository.JokeRepository;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JokeController {

    @Autowired
    private JokeRepository jokeRepository;

    @GetMapping("/jokes")
    public List<JokeData> getJokes() {
        return jokeRepository.getAllJokes();
    }

    @GetMapping("/jokes/{id}")
    public ResponseEntity<JokeData> getJokeById(@PathVariable int id) {
        Optional<JokeData> joke = jokeRepository.getJokeById(id);
        return joke.map(j -> new ResponseEntity<>(j, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/jokes")
    public ResponseEntity<Void> addJoke(@RequestBody JokeData joke) {
        jokeRepository.addJoke(joke);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("jokes/{id}")
    public ResponseEntity<Void> updateJoke(@PathVariable int id, @RequestBody JokeData updatedJoke) {
        updatedJoke.setId(id);
        if (jokeRepository.updateJoke(updatedJoke)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } 

    @DeleteMapping("jokes/{id}")
    public ResponseEntity<Void> deleteJoke(@PathVariable int id) {
        if (jokeRepository.deleteJoke(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
