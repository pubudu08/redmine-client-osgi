package org.wso2.carbon.utility.redmine;


import org.wso2.carbon.utility.redmine.service.PMAdminService;


/**
 * Created by pubudu on 2/3/14.
 */
public class Sample {

    public static String redmineHost = "https://redmine.wso2.com/";
    public static String usename = "pubudud@wso2.com";
    public static String password = "<password>";


    public static void main(String[] args) {


        PMAdminService pmAdminService = new PMAdminService();


        System.out.println("" +
                "Is Carbon Kernel exist ? : " + pmAdminService.isPMSProjectExist(redmineHost, usename,password, "Jaggery") + "\n" +
                pmAdminService.getCurrentUser(redmineHost, usename,password) + "\n" +
                pmAdminService.getProjectById(redmineHost, usename,password,"18") + "\n" +
                "Parent of Carbon kernel is "+pmAdminService.getProjectById(redmineHost, usename,password,pmAdminService.getProjectById(redmineHost, usename,password,"13").getParentId().toString()) + "\n" +
                pmAdminService.getProjectById(redmineHost, usename,password,"13").getIdentifier() + "\n" +
                pmAdminService.getProjectVersions(redmineHost, usename,password,"13") + "\n"
        );

       //System.out.println(pmAdminService.createPMSProject(null, null, "Hello World"));




    }
}
