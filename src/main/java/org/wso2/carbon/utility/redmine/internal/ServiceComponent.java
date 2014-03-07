package org.wso2.carbon.utility.redmine.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.utility.projectmanagement.IProjectManagement;
import org.wso2.carbon.utility.redmine.service.PMAdminService;

/**
 * Created by pubudu on 2/3/14.
 * @scr.component name="org.wso2.carbon.utility.redmine"
 * immediate="true"
 */
public class ServiceComponent {

    private ServiceRegistration serviceRegistry;
    private static BundleContext  bundleContext;
    private static PMAdminService pmAdminService;
    private static final Log logger = LogFactory.getLog(ServiceComponent.class);


    public void activate(ComponentContext componentContext){
        logger.info("Project management Service: Redmine bundle is activated");
        pmAdminService= new PMAdminService();
        bundleContext = componentContext.getBundleContext();
        serviceRegistry = bundleContext.registerService(IProjectManagement.class.getName(),pmAdminService, null);

    }

    public void deactivate(ComponentContext componentContext){
        logger.info("Project management Service: Redmine bundle is deactivated");
        serviceRegistry.unregister();
        bundleContext=null;
        pmAdminService=null;
    }

}
