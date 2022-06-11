package io.aadesh.yogdaan.service;

import io.aadesh.yogdaan.entity.EventDonations;
import io.aadesh.yogdaan.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;
    public void saveEventDonations(EventDonations ed){
        donationRepository.save(ed);
    }
    public List<EventDonations> getEventsByUser(String userId){
        return donationRepository.findAllByUserId(userId);
    }
}
