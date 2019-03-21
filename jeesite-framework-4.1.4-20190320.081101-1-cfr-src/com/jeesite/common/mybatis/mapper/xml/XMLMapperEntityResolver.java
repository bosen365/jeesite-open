/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.mybatis.mapper.xml;	
	
import java.io.IOException;	
import java.io.InputStream;	
import java.util.Locale;	
import org.apache.ibatis.io.Resources;	
import org.xml.sax.EntityResolver;	
import org.xml.sax.InputSource;	
import org.xml.sax.SAXException;	
	
public class XMLMapperEntityResolver	
implements EntityResolver {	
    private static final String MYBATIS_MAPPER_SYSTEM = "mybatis-3-mapper.dtd";	
    private static final String IBATIS_MAPPER_SYSTEM = "ibatis-3-mapper.dtd";	
    private static final String MYBATIS_CONFIG_DTD = "org/apache/ibatis/builder/xml/mybatis-3-config.dtd";	
    private static final String MYBATIS_MAPPER_DTD = "com/jeesite/common/mybatis/mapper/xml/mybatis-3-mapper.dtd";	
    private static final String MYBATIS_CONFIG_SYSTEM = "mybatis-3-config.dtd";	
    private static final String IBATIS_CONFIG_SYSTEM = "ibatis-3-config.dtd";	
	
    private /* synthetic */ InputSource getInputSource(String path, String publicId, String systemId) {	
        InputSource a = null;	
        if (path != null) {	
            try {	
                InputStream a2 = Resources.getResourceAsStream(path);	
                InputSource inputSource = a = new InputSource(a2);	
                inputSource.setPublicId(publicId);	
                inputSource.setSystemId(systemId);	
                return a;	
            }	
            catch (IOException a2) {	
                // empty catch block	
            }	
        }	
        return a;	
    }	
	
    @Override	
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException {	
        block4 : {	
            String a;	
            block5 : {	
                try {	
                    if (systemId == null) break block4;	
                    a = systemId.toLowerCase(Locale.ENGLISH);	
                    if (!a.contains(MYBATIS_CONFIG_SYSTEM) && !a.contains(IBATIS_CONFIG_SYSTEM)) break block5;	
                    return this.getInputSource(MYBATIS_CONFIG_DTD, publicId, systemId);	
                }	
                catch (Exception a2) {	
                    throw new SAXException(a2.toString());	
                }	
            }	
            if (!a.contains(MYBATIS_MAPPER_SYSTEM) && !a.contains(IBATIS_MAPPER_SYSTEM)) break block4;	
            return this.getInputSource(MYBATIS_MAPPER_DTD, publicId, systemId);	
        }	
        return null;	
    }	
}	
	
