package com.jeesite.common.mybatis.mapper.xml;	
	
import java.io.IOException;	
import java.io.InputStream;	
import java.util.Locale;	
import org.apache.ibatis.io.Resources;	
import org.xml.sax.EntityResolver;	
import org.xml.sax.InputSource;	
import org.xml.sax.SAXException;	
	
public class XMLMapperEntityResolver implements EntityResolver {	
   private static final String MYBATIS_MAPPER_SYSTEM = "mybatis-3-mapper.dtd";	
   private static final String IBATIS_CONFIG_SYSTEM = "ibatis-3-config.dtd";	
   private static final String MYBATIS_CONFIG_DTD = "org/apache/ibatis/builder/xml/mybatis-3-config.dtd";	
   private static final String MYBATIS_MAPPER_DTD = "com/jeesite/common/mybatis/mapper/xml/mybatis-3-mapper.dtd";	
   private static final String MYBATIS_CONFIG_SYSTEM = "mybatis-3-config.dtd";	
   private static final String IBATIS_MAPPER_SYSTEM = "ibatis-3-mapper.dtd";	
	
   // $FF: synthetic method	
   private InputSource getInputSource(String path, String publicId, String systemId) {	
      InputSource a = null;	
      if (path != null) {	
         try {	
            InputStream a = Resources.getResourceAsStream(path);	
            a = new InputSource(a);	
            a.setPublicId(publicId);	
            a.setSystemId(systemId);	
            return a;	
         } catch (IOException var6) {	
         }	
      }	
	
      return a;	
   }	
	
   public InputSource resolveEntity(String publicId, String systemId) throws SAXException {	
      try {	
         if (systemId != null) {	
            String a;	
            if ((a = systemId.toLowerCase(Locale.ENGLISH)).contains("mybatis-3-config.dtd") || a.contains("ibatis-3-config.dtd")) {	
               return this.getInputSource("org/apache/ibatis/builder/xml/mybatis-3-config.dtd", publicId, systemId);	
            }	
	
            if (a.contains("mybatis-3-mapper.dtd") || a.contains("ibatis-3-mapper.dtd")) {	
               return this.getInputSource("com/jeesite/common/mybatis/mapper/xml/mybatis-3-mapper.dtd", publicId, systemId);	
            }	
         }	
	
         return null;	
      } catch (Exception var4) {	
         throw new SAXException(var4.toString());	
      }	
   }	
}	
