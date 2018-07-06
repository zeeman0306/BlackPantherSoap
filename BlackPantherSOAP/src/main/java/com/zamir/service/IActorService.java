package com.zamir.service;

import java.util.List;
import com.zamir.entity.Actor;


public interface IActorService {
    List<Actor> getAllActors();
    Actor getActorById(long actorId);
    boolean addActor(Actor actor);
    void updateActor(Actor actor);
    void deleteActor(Actor actor);
}
