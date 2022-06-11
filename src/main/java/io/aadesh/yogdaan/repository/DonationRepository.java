package io.aadesh.yogdaan.repository;

import io.aadesh.yogdaan.entity.Event;
import io.aadesh.yogdaan.entity.EventDonations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<EventDonations,Integer> {
    List<EventDonations> findAllByUserId(String userId);
}
