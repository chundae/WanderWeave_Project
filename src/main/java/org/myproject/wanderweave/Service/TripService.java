package org.myproject.wanderweave.Service;

import org.myproject.wanderweave.Domain.Trip;
import org.myproject.wanderweave.Repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TripService implements TripService_ITF{

    @Autowired
    private TripRepository tripRepository;


    //Trip추가
    @Override
    public Trip addTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    //Trip 디테일로딩
    @Override
    public Optional<Trip> viewTrip(Long tripId) {
        Optional<Trip> viewTrip = Optional.ofNullable(tripRepository.findByTripId(tripId));
        if(viewTrip.isPresent()) {
            Trip trip = viewTrip.get();
            return Optional.of(trip);
        }
        return Optional.empty();
    }
    //mainTrip리스트 로딩
    @Override
    public List<Trip> viewAllTrips(Long userId) {
        return tripRepository.findAllByUserId(userId);
    }

    //trip 삭제
    @Override
    @Transactional
    public void deleteTrip(Long tripId) {
        tripRepository.deleteByTripId(tripId);
    }

    //trip수정
    @Override
    @Transactional
    public void updateTrip(Trip trip, Long tripId) {
        //기존 정보 조회
        Trip existingTrip = tripRepository.findByTripId(tripId);

        //업데이트
        existingTrip.setTripName(trip.getTripName());
        existingTrip.setStart_date(trip.getStart_date());
        existingTrip.setEnd_date(trip.getEnd_date());
        existingTrip.setDescription((trip.getDescription()));
        //업데이트 엔티티 저장
        tripRepository.save(existingTrip);
    }
}
