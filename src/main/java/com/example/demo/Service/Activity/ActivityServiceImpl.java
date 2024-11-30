package com.example.demo.Service.Activity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.ActivityDTO;
import com.example.demo.Entity.Activity;
import com.example.demo.Repository.ActivityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    @Override
    public ActivityDTO postActivity(ActivityDTO dto) {
        Activity activity;
    
        // If the DTO has an ID, it's an update operation
        if (dto.getId() != null) {
            // Fetch the existing activity from the repository
            activity = activityRepository.findById(dto.getId()).orElseThrow();
        } else {
            // If there's no ID, it's a new record, so create a new Activity
            activity = new Activity();
        }
    
        // Set the values from the DTO to the activity
        activity.setDate(dto.getDate());
        activity.setSteps(dto.getSteps());
        activity.setDistance(dto.getDistance());
        activity.setCaloriesBurned(dto.getCaloriesBurned());
    
        // Save the activity (this will update an existing record if ID is provided)
        return activityRepository.save(activity).getActivityDTO();
    }
@Override
    public List<ActivityDTO> getActivities() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream().map(Activity::getActivityDTO).collect(Collectors.toList());
    }

    @Override
    public ActivityDTO getActivityById(Long id) {
        return activityRepository.findById(id).orElseThrow().getActivityDTO();
    }

    @Override
    public void updateActivity(Long id, ActivityDTO dto) {
        Activity activity = activityRepository.findById(id).orElseThrow();
        activity.setDate(dto.getDate());
        activity.setSteps(dto.getSteps());
        activity.setDistance(dto.getDistance());
        activity.setCaloriesBurned(dto.getCaloriesBurned());
        activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }
}
