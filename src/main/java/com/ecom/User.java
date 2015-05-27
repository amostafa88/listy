package com.ecom;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public abstract class User implements Serializable{

	@Id
    protected String id;
	
	protected String first_name;
	
	protected String last_name;
	
	
}
