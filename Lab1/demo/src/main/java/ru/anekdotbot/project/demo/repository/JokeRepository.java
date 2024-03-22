package ru.anekdotbot.project.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ru.anekdotbot.project.demo.model.JokeData;

@Repository
public class JokeRepository {
    private List<JokeData> jokes = new ArrayList<>();
    private int nextId = 1;

    public List<JokeData> getAllJokes(){
        return jokes;
    }

    public Optional<JokeData> getJokeById(int id){
        return jokes.stream().filter(j -> j.getId() == id).findFirst();
    }

    public void addJoke(JokeData joke){
        joke.setId(nextId++);
        jokes.add(joke);
    }

    public boolean updateJoke(JokeData updatedJoke){
        for (int i = 0; i < jokes.size(); i++) {
            JokeData joke = jokes.get(i);
            if (joke.getId() == updatedJoke.getId()) {
                jokes.set(i, updatedJoke);
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteJoke(int id) {
        return jokes.removeIf(j -> j.getId() == id);
    }
}
