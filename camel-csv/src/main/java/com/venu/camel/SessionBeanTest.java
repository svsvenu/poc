package com.venu.camel;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SessionBeanTest implements Runnable {

	private  Context ctx = null;
	
	public SessionBeanTest(Context ctx){
		
		this.ctx = ctx;
	}
	
	
	public static void main(String[] args) {
		
		try{

Properties jndiProps = new Properties();
jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
jndiProps.put(Context.PROVIDER_URL,"remote://localhost:4447");
jndiProps.put("jboss.naming.client.ejb.context", true);
// create a context passing these properties
Context ctx = new InitialContext(jndiProps);

	SessionBeanTest sbt = new SessionBeanTest(ctx);
	
	sbt.run();
	
	ExecutorService pool = Executors.newFixedThreadPool(30);

	for (int i = 0; i < 300; i++) {
	    pool.submit(new SessionBeanTest(ctx));
	}

// lookup

		}
		
		catch(Exception e)
		{
			
			e.printStackTrace();
			
		}
		
	}
	
public void run() {
	// TODO Auto-generated method stub
	
	System.out.println("started" + Thread.currentThread().getId());
	
	SampleSessionBeanRemote scr;
	try {
		scr = (SampleSessionBeanRemote)ctx.lookup("SessionBeanTestEar/SessionBeanTestEJB/SampleSessionBean!com.venu.SampleSessionBeanRemote");
	
		System.out.println("Done method on scr" + scr.addOne(100L));

	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}




	
}	
	
	}




