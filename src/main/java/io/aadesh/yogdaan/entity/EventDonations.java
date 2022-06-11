package io.aadesh.yogdaan.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventDonations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int donationId;
    private String userId;
    private int eventId;
    private String eventName;
    private String ngo;
    private String donationMode;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getNgo() {
        return ngo;
    }

    public void setNgo(String ngo) {
        this.ngo = ngo;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDonationMode() {
        return donationMode;
    }

    public void setDonationMode(String donationMode) {
        this.donationMode = donationMode;
    }
}
