package com.findsomeone.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class NearbyMeetupResponse {

    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String locationName;
    private Double latitude;
    private Double longitude;
    private Double distanceKm;
    private Integer availableSpots;

    public NearbyMeetupResponse(
            Long id,
            String title,
            String description,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime,
            String locationName,
            Double latitude,
            Double longitude,
            Double distanceKm,
            Integer availableSpots) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceKm = distanceKm;
        this.availableSpots = availableSpots;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getLocationName() {
        return locationName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public Integer getAvailableSpots() {
        return availableSpots;
    }
}
