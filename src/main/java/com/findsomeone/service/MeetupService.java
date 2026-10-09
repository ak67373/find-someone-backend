package com.findsomeone.service;

import com.findsomeone.entity.Meetup;
import com.findsomeone.entity.MeetupParticipant;
import com.findsomeone.entity.MeetupStatus;
import com.findsomeone.entity.User;
import com.findsomeone.repository.MeetupParticipantRepository;
import com.findsomeone.repository.MeetupRepository;
import com.findsomeone.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MeetupService {

    private final MeetupRepository meetupRepository;
    private final UserRepository userRepository;
    private final MeetupParticipantRepository meetupParticipantRepository;

    public MeetupService(
            MeetupRepository meetupRepository,
            UserRepository userRepository,
            MeetupParticipantRepository meetupParticipantRepository) {

        this.meetupRepository = meetupRepository;
        this.userRepository = userRepository;
        this.meetupParticipantRepository = meetupParticipantRepository;
    }


    public Meetup createMeetup(Meetup meetup, Long creatorId) {

        User creator = userRepository.findById(creatorId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "User not found"
                        ));

        meetup.setCreator(creator);
        meetup.setStatus(MeetupStatus.OPEN);

        Meetup savedMeetup = meetupRepository.save(meetup);

        // Add creator as the first participant
        MeetupParticipant participant = new MeetupParticipant();
        participant.setMeetup(savedMeetup);
        participant.setUser(creator);

        meetupParticipantRepository.save(participant);

        return savedMeetup;
    }

    public Meetup getMeetup(Long id) {
        return meetupRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Meetup not found"
                        ));
    }

    public List<Meetup> getAllMeetups() {
        return meetupRepository.findAll();
    }

    public void updateMeetupStatus(Long meetupId) {

        Meetup meetup = meetupRepository.findById(meetupId)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Meetup not found"
                        ));

        long participantCount =
                meetupParticipantRepository.countByMeetupId(meetupId);

        if (participantCount >= meetup.getMaxParticipants()) {
            meetup.setStatus(MeetupStatus.FULL);
            meetupRepository.save(meetup);
        }
    }



}
