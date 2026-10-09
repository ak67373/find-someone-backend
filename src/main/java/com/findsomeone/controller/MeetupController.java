package com.findsomeone.controller;

import com.findsomeone.dto.CreateMeetupRequest;
import com.findsomeone.entity.Meetup;
import com.findsomeone.service.MeetupService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MeetupController {

    private final MeetupService meetupService;

    public MeetupController(MeetupService meetupService) {
        this.meetupService = meetupService;
    }

    @PostMapping("/meetups")
    public Meetup createMeetup(
            @Valid @RequestBody CreateMeetupRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        Long creatorId = (Long) authentication.getPrincipal();

        Meetup meetup = new Meetup();

        meetup.setTitle(request.getTitle());
        meetup.setDescription(request.getDescription());
        meetup.setDate(request.getDate());
        meetup.setStartTime(request.getStartTime());
        meetup.setEndTime(request.getEndTime());
        meetup.setLocationName(request.getLocationName());
        meetup.setLatitude(request.getLatitude());
        meetup.setLongitude(request.getLongitude());
        meetup.setMaxParticipants(request.getMaxParticipants());

        return meetupService.createMeetup(meetup, creatorId);
    }

    @GetMapping("/meetups/{id}")
    public Meetup getMeetup(@PathVariable Long id) {
        return meetupService.getMeetup(id);
    }

    @GetMapping("/meetups")
    public List<Meetup> getAllMeetups() {
        return meetupService.getAllMeetups();
    }

    @PatchMapping("/meetups/{meetupId}/status")
    public void updateMeetupStatus(@PathVariable Long meetupId) {
        meetupService.updateMeetupStatus(meetupId);
    }
}
