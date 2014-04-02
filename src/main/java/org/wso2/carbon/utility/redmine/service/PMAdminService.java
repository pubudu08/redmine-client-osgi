package org.wso2.carbon.utility.redmine.service;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.bean.Version;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utility.projectmanagement.IProjectManagement;

import java.util.Date;
import java.util.List;

/**
 * PMAdminService is a class which implements IProjectManagement
 * ReadMine API faces against the pulggable PM interface
 * @author Pubudu Dissanayake : pubudud@wso2.com  on 02/03/2014.
 */
public class PMAdminService implements IProjectManagement{

    private static final Log logger = LogFactory.getLog(PMAdminService.class);
    private RedmineManager mgr = null;

    /**
     * This method will returns artifact type
     * @return <code>Redmine</code>
     **/
    @Override
    public String getPMSType() {
        return "Redmine";
    }
    /**
     * This method will create a project which is related to PM
     * @param pmHost the host name to create a project
     * @param username username
     * @param password password
     * @param projectName  project name
     * @return  <code>true</code> if project created successfully
     */
    @Override
    public boolean createPMSProject(String redmineHost, String username,String password,  String projectName) {

        mgr=  new RedmineManager(redmineHost,username,password);
        Project project=null;
        try {
            logger.info(mgr.getCurrentUser());
            project = new Project( );
            project.setName(projectName);
            projectName  = projectName.replaceAll(" ", "-");
            project.setIdentifier(projectName);
            project.setCreatedOn(new Date());
            project.setIdentifier(projectName);
            mgr.createProject(project);
            return isPMSProjectExist(redmineHost,username,password,projectName);

        } catch (RedmineException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * check whether project is exists or not
     * @param pmHost   Host name of the API
     * @param username username
     * @param password password
     * @param projectName  name of the project to search
     * @return  <code>true</code> if project already exists
     */
    @Override
    public boolean isPMSProjectExist(String redmineHost, String username,String password, String projectName) {
        mgr = new RedmineManager(redmineHost,username,password);
        try {
            logger.info(mgr.getCurrentUser());
            List<Project> projects = mgr.getProjects();
            for(Project project:projects){
                if (projectName.equals(project.getName())){
                    return true;
                }
            }
        } catch (RedmineException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * get project by its Id
     * @param redmineHost
     * @param username username
     * @param password password
     * @param projectkey
     * @return Redmine Project
     */
    public Project getProjectById(String redmineHost, String username,String password, String projectkey){
        mgr= new RedmineManager(redmineHost,username,password);
        Project project = null;
        try {
            project = mgr.getProjectByKey(projectkey);

        } catch (RedmineException e) {
            e.printStackTrace();
        }

        return project;
    }

    /**
     * get the current logged user
     * @param redmineHost
     * @param username username
     * @param password password
     * @return User type object
     */
    public User getCurrentUser(String redmineHost, String username,String password ){
        mgr = new RedmineManager(redmineHost,username,password);
        User user = null;
        try {
            user = mgr.getCurrentUser();
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return user;

    }

    /**
     * Get list of projects
     * @param redmineHost
     * @param username username
     * @param password password
     * @param projectKey
     * @return Array list of projects
     */
    public List<Version> getProjectVersions(String redmineHost, String username,String password, String projectKey){
        mgr = new RedmineManager(redmineHost,username,password);
        List<Version> projectVersions = null;

        try {
            projectVersions = mgr.getVersions(Integer.parseInt(projectKey));
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return projectVersions;

    }



}
