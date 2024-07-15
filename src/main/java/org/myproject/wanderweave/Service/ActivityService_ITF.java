package org.myproject.wanderweave.Service;

import org.myproject.wanderweave.Domain.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService_ITF {
    Activity addActivity(Activity activity);

    Optional<Activity> viewActivity(Long ActivityId);

    List<Activity> viewAllActivity(Long tripId);

    void deleteActivity(Long activityId);

    void updateActivity(Activity activity, Long activityId);
}
