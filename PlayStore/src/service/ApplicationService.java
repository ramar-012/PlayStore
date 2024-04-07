package service;
import dao.ApplicationDAO;
import model.Application;

import java.util.List;

public class ApplicationService {
    private final ApplicationDAO applicationDAO;

    public ApplicationService() {
        this.applicationDAO = new ApplicationDAO();
    }

    //create application
    public void createApplication(Application application) {
        applicationDAO.createApplication(application);
    }

    //get application by id
    public Application getApplicationById(int app_id) {
        return applicationDAO.getApplicationById(app_id);
    }

    //get maxID
    public int getMaxAppId() {
        return applicationDAO.getMaxAppId();
    }
    //get all applications
    public List<Application> getAllApplications() {
        return applicationDAO.getAllApplications();
    }

    //update application
    public void updateApplication(Application application) {
        applicationDAO.updateApplication(application);
    }

    //delete application
    public void deleteApplication(int app_id) {
        applicationDAO.deleteApplication(app_id);
    }

    //get application by its name
    public List<Application> getApplicationByName(String name) {
        return applicationDAO.searchApplicationByName(name);
    }
    //get application by its genre
    public List<Application> getApplicationByGenre(String genre) {
        return applicationDAO.searchApplicationByGenre(genre);
    }

}
