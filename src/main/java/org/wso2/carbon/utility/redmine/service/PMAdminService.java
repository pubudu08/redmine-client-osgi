package org.wso2.carbon.utility.redmine.service;

import com.taskadapter.redmineapi.RedmineException;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.bean.Project;
import com.taskadapter.redmineapi.bean.User;
import com.taskadapter.redmineapi.bean.Version;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.utility.projectmanagement.ProjectManagementArtifact;
import org.wso2.carbon.utility.redmine.exception.GenericArtifactException;

import java.util.Date;
import java.util.List;

/**
 * PMAdminService is a class which implements IProjectManagement
 * ReadMine API faces against the pulggable PM interface
 *
 * @author Pubudu Dissanayake : pubudud@wso2.com  on 02/03/2014.
 */
public class PMAdminService implements ProjectManagementArtifact {

	private static final Log LOGGER = LogFactory.getLog(PMAdminService.class);
	private RedmineManager redmineManager = null;

	/**
	 * This method will returns artifact type
	 *
	 * @return <code>Redmine</code>
	 */
	@Override
	public String getPMSType() {
		return "Redmine";
	}

	/**
	 * This method will create a project which is related to PM
	 *
	 * @param pmHost      the host name to create a project
	 * @param username    username
	 * @param password    password
	 * @param projectName project name
	 * @return <code>true</code> if project created successfully
	 */
	@Override
	public void createPMSProject(String redmineHost, String username,
	                             String password, String projectName)
	  throws GenericArtifactException, RedmineException {

		Project project;
		redmineManager = new RedmineManager(redmineHost, username, password);
		try {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Redmine account login user : " + redmineManager.getCurrentUser());
			}
			for (Project tempProject : redmineManager.getProjects()) {
				if (projectName.equals(tempProject.getName())) {
					throw new GenericArtifactException(
					  "Project is already exists, Please provide" +
					  "a suitable project name. ", "Project_Already_Exists");
				}
			}
			project = new Project();
			project.setName(projectName);
			projectName = projectName.replaceAll(" ", "-");
			project.setIdentifier(projectName);
			project.setCreatedOn(new Date());
			project.setIdentifier(projectName);
			redmineManager.createProject(project);

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Redmine project successfully created by : " +
				             redmineManager.getCurrentUser() + ", timestamp " + new Date().getTime());
			}
		} catch (RedmineException exception) {
			throw new GenericArtifactException("Unable to connect Redmine servers",
			  exception, "Redmine_Server_Error");
		} finally {
			if (redmineManager != null) {
				redmineManager = null;
			}
		}
	}

	/**
	 * get project by its Id
	 *
	 * @param redmineHost redmine hostname
	 * @param username    username
	 * @param password    password
	 * @param projectKey
	 * @return Redmine Project
	 */
	public Project getProjectById(String redmineHost, String username,
	                              String password, String projectKey)
	  throws RedmineException, GenericArtifactException {
		redmineManager = new RedmineManager(redmineHost, username, password);
		Project project;
		try {
			project = redmineManager.getProjectByKey(projectKey);
		} catch (RedmineException exception) {
			throw new GenericArtifactException("Unable to connect Redmine servers",
			  exception, "Redmine_Server_Error");
		}
		return project;
	}

	/**
	 * get the current logged user
	 *
	 * @param redmineHost redmine hostname
	 * @param username    username
	 * @param password    password
	 * @return User type object
	 */
	public User getCurrentUser(String redmineHost,
	                           String username,
	                           String password)
	  throws RedmineException, GenericArtifactException {
		redmineManager = new RedmineManager(redmineHost, username, password);
		User user;
		try {
			user = redmineManager.getCurrentUser();
		} catch (RedmineException exception) {
			throw new GenericArtifactException("Unable to connect Redmine servers",
			  exception, "Redmine_Server_Error");
		}
		return user;
	}

	/**
	 * Get list of projects
	 *
	 * @param redmineHost redmine hostname
	 * @param username    username
	 * @param password    password
	 * @param projectKey  project key
	 * @return Array list of projects
	 */
	public List<Version> getProjectVersions(String redmineHost,
	                                        String username,
	                                        String password,
	                                        String projectKey)
	  throws RedmineException, GenericArtifactException {
		redmineManager = new RedmineManager(redmineHost, username, password);
		List<Version> projectVersions;

		try {
			projectVersions = redmineManager.getVersions(Integer.parseInt(projectKey));
		} catch (RedmineException exception) {
			throw new GenericArtifactException("Unable to connect Redmine servers", exception,
			  "Redmine_Server_Error");
		}
		return projectVersions;


	}
}
