package io.aadesh.yogdaan.repository;

import io.aadesh.yogdaan.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Integer> {
    List<Event> findAllByUserId(String userId);
}
