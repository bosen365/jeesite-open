package com.jeesite.common.service;	
	
public class ServiceException extends RuntimeException {	
   private static final long serialVersionUID = 1L;	
	
   public ServiceException() {	
   }	
	
   public ServiceException(String message, Throwable cause) {	
      super(message, cause);	
   }	
	
   public ServiceException(Throwable cause) {	
      super(cause);	
   }	
	
   public ServiceException(String message) {	
      super(message);	
   }	
}	
