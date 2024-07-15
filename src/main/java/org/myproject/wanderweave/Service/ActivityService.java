package org.myproject.wanderweave.Service;

import org.myproject.wanderweave.Domain.Activity;
import org.myproject.wanderweave.Repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService implements ActivityService_ITF{

    @Autowired
    private ActivityRepository activityRepository;

    //activity추가
    @Override
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    //detail이동
    @Override
    public Optional<Activity> viewActivity(Long ActivityId) {
        Optional<Activity> viewActivity = activityRepository.findById(ActivityId);
        if (viewActivity.isPresent()) {
            Activity activity = viewActivity.get();
            return Optional.of(activity);
        }
        return Optional.empty();
    }

    //trip_detail에 활동리스트 출력
    @Override
    public List<Activity> viewAllActivity(Long tripId) {
        return activityRepository.findAllByTripId(tripId);
    }

    //삭제
    @Override
    @Transactional
    public void deleteActivity(Long id) {
        activityRepository.deleteByActivityId(id);
    }

    //수정
    @Override
    @Transactional
    public void updateActivity(Activity activity, Long activityId) {
        //기존 정보 조회
        Activity existingAct = activityRepository.findById(activityId).get();

        //업데이트 엔티티 적용
        existingAct.setActivityName(activity.getActivityName());
        existingAct.setDate(activity.getDate());
        existingAct.setTime(activity.getTime());
        existingAct.setLocation(activity.getLocation());
        existingAct.setDescription(activity.getDescription());

        //업데이트 저장
        activityRepository.save(existingAct);
    }
}
