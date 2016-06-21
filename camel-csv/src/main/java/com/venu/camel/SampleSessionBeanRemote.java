package com.venu.camel;

import javax.ejb.Remote;

@Remote
public interface SampleSessionBeanRemote {
	
	
	public int addOne(long number) ;

}
