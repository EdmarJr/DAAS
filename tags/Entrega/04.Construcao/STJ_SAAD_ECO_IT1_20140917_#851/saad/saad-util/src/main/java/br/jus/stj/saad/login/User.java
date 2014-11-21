package br.jus.stj.saad.login;


import java.security.Principal;
import java.util.Set;

public class User implements Principal{
 	private String name;
 	private Set roles;
 	
 	public User(String name){
 		this.name = name;
 	}
 	
 	public String getName() {
 		return name;
 	}
 	
 	public Set getRoles() {
 		return roles;
 	}
 
 	public void setRoles(Set roles) {
 		if (this.roles == null)
 			this.roles = roles;
 	}
 }
