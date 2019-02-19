package com.jeesite.autoconfigure.sys;	
	
import com.jeesite.modules.file.service.FileEntityService;	
import com.jeesite.modules.file.service.FileUploadService;	
import com.jeesite.modules.file.service.FileUploadServiceExtend;	
import com.jeesite.modules.file.service.support.FileEntityServiceSupport;	
import com.jeesite.modules.file.service.support.FileUploadServiceExtendSupport;	
import com.jeesite.modules.file.service.support.FileUploadServiceSupport;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;	
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;	
import org.springframework.context.annotation.Bean;	
import org.springframework.context.annotation.Configuration;	
	
@Configuration	
@ConditionalOnProperty(	
   name = {"file.enabled"},	
   havingValue = "true",	
   matchIfMissing = true	
)	
public class FileAutoConfiguration {	
   @Bean	
   @ConditionalOnMissingBean	
   public FileUploadService fileUploadService() {	
      return new FileUploadServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public FileEntityService fileEntityService() {	
      return new FileEntityServiceSupport();	
   }	
	
   @Bean	
   @ConditionalOnMissingBean	
   public FileUploadServiceExtend fileUploadServiceExtend() {	
      return new FileUploadServiceExtendSupport();	
   }	
}	
