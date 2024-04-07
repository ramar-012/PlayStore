package controller;
import model.Application;
import service.ApplicationService;

import java.util.List;

public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController() {
        applicationService = new ApplicationService();
    }

    //create application
    public void createApplication(Application application) {
        applicationService.createApplication(application);
    }

    //get application by id
    public Application getApplicationById(int app_id) {
        return applicationService.getApplicationById(app_id);
    }

    //update application
    public void updateApplication(Application application) {
        applicationService.updateApplication(application);
        System.out.println("Application updated successfully!");
    }

    //get maxID
    public int getMaxAppId() {
        return applicationService.getMaxAppId();
    }

    //delete application
    public void deleteApplication(int app_id) {
        applicationService.deleteApplication(app_id);
    }

    //get applications by its name
    public List<Application> getApplicationByName(String name) {
        return applicationService.getApplicationByName(name);
    }

    //get applications by its genre
    public List<Application> getApplicationByGenre(String genre) {
        return applicationService.getApplicationByGenre(genre);
    }
}
