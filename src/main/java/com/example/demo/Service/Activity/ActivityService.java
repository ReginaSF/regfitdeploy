package com.example.demo.Service.Activity;

import java.util.List;

import com.example.demo.DTO.ActivityDTO;

public interface ActivityService {

public ActivityDTO postActivity(ActivityDTO dto);

public List<ActivityDTO> getActivities();

ActivityDTO getActivityById(Long id);

void updateActivity(Long id, ActivityDTO dto);

void deleteActivity(Long id);
}
