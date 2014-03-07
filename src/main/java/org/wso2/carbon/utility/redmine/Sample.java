package org.wso2.carbon.utility.redmine;


import org.wso2.carbon.utility.redmine.service.PMAdminService;


/**
 * Created by pubudu on 2/3/14.
 */
public class Sample {

    public static String redmineHost = "https://redmine.wso2.com/";
    public static String apiAccessKey = "cd1a2076f7d7a155a0e13e2b05553ac26e27eeb7";


    public static void main(String[] args) {


        PMAdminService pmAdminService = new PMAdminService();


        System.out.println("" +
                "Is Carbon Kernel exist ? : " + pmAdminService.isPMSProjectExist(redmineHost, apiAccessKey, "Jaggery") + "\n" +
                pmAdminService.getCurrentUser(redmineHost,apiAccessKey) + "\n" +
                pmAdminService.getProjectById(redmineHost,apiAccessKey,"18") + "\n" +
                "Parent of Carbon kernel is "+pmAdminService.getProjectById(redmineHost,apiAccessKey,pmAdminService.getProjectById(redmineHost,apiAccessKey,"13").getParentId().toString()) + "\n" +
                pmAdminService.getProjectById(redmineHost,apiAccessKey,"13").getIdentifier() + "\n" +
                pmAdminService.getProjectVersions(redmineHost,apiAccessKey,"13") + "\n"
        );

       //System.out.println(pmAdminService.createPMSProject(null, null, "Hello World"));




    }
}
