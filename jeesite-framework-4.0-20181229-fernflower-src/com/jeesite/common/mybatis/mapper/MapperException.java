package com.jeesite.common.mybatis.mapper;	
	
public class MapperException extends RuntimeException {	
   private static final long serialVersionUID = 1L;	
	
   public MapperException(String message, Throwable cause) {	
      super(message, cause);	
   }	
	
   public MapperException() {	
   }	
	
   public MapperException(Throwable cause) {	
      super(cause);	
   }	
	
   public MapperException(String message) {	
      super(message);	
   }	
}	
