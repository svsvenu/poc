package com.venu.camel;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.cdi.ContextName;

/**
 * Session Bean implementation class SampleSessionBean
 */
@Stateless
@LocalBean
public class SampleSessionBean implements SampleSessionBeanRemote, SampleSessionBeanLocal {

    /**
     * Default constructor. 
     */
	
	  @Inject
	    @ContextName("cdi-context")
	    private CamelContext camelctx;
	
	
    public SampleSessionBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
    public void initialize () {
        // Initialize here objects which will be used
        // by the session bean
        System.out.println("SessionDemoBean initialized.");
        if ( camelctx == null ){
        	
        	System.out.println("camel context is null");
        }
        
        else {
        	
        	System.out.println("camel context is not null");

        	
        }
        
    }


	@Override
	public int addOne(long number) {
		// TODO Auto-generated method stub
		try {
			 Thread.sleep(30000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int) (number + 1);
	} 
 

}
