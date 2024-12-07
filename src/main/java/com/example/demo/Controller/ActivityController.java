package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.ActivityDTO;
import com.example.demo.Service.Activity.ActivityService;

@Controller
public class ActivityController { 
   @Autowired
    
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/new")
    public String showNewActivityForm(Model model) {
        model.addAttribute("activityDTO", new ActivityDTO());
        
        return "activityForm"; 
    }

    @PostMapping("/new")
    public String postNewActivity(@ModelAttribute ActivityDTO activityDTO) {
        activityService.postActivity(activityDTO);
        return "redirect:/activities"; 
    }

    @GetMapping("/activities")
    public String enlistActivities(Model model) {
        model.addAttribute("activities", activityService.getActivities());
        return "activityList";
    }
     // Método para mostrar el formulario de edición de actividad
    @GetMapping("/activities/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        ActivityDTO activityDTO = activityService.getActivityById(id);
        model.addAttribute("activityDTO", activityDTO);
        return "activityForm"; // Reutiliza el mismo formulario para editar
    }

    // Método para actualizar la actividad
    @PostMapping("/activities/{id}/edit")
    public String updateActivity(@PathVariable Long id, @ModelAttribute ActivityDTO activityDTO) {
        activityService.updateActivity(id, activityDTO);
        return "redirect:/activities";
    }

    // Método para eliminar una actividad
    @PostMapping("/activities/{id}/delete")
    public String deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return "redirect:/activities";
    }
}