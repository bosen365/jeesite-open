/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.ibatis.io.Resources	
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
    private static final String IBATIS_CONFIG_SYSTEM = "ibatis-3-config.dtd";	
    private static final String MYBATIS_CONFIG_DTD = "org/apache/ibatis/builder/xml/mybatis-3-config.dtd";	
    private static final String MYBATIS_MAPPER_DTD = "com/jeesite/common/mybatis/mapper/xml/mybatis-3-mapper.dtd";	
    private static final String MYBATIS_CONFIG_SYSTEM = "mybatis-3-config.dtd";	
    private static final String IBATIS_MAPPER_SYSTEM = "ibatis-3-mapper.dtd";	
	
    private /* synthetic */ InputSource getInputSource(String path, String publicId, String systemId) {	
        InputSource a2 = null;	
        if (path != null) {	
            try {	
                InputStream a3 = Resources.getResourceAsStream((String)path);	
                InputSource inputSource = a2 = new InputSource(a3);	
                inputSource.setPublicId(publicId);	
                inputSource.setSystemId(systemId);	
                return a2;	
            }	
            catch (IOException a3) {	
                // empty catch block	
            }	
        }	
        return a2;	
    }	
	
    @Override	
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException {	
        block4 : {	
            String a2;	
            block5 : {	
                try {	
                    if (systemId == null) break block4;	
                    a2 = systemId.toLowerCase(Locale.ENGLISH);	
                    if (!a2.contains(MYBATIS_CONFIG_SYSTEM) && !a2.contains(IBATIS_CONFIG_SYSTEM)) break block5;	
                    return this.getInputSource(MYBATIS_CONFIG_DTD, publicId, systemId);	
                }	
                catch (Exception a3) {	
                    throw new SAXException(a3.toString());	
                }	
            }	
            if (!a2.contains(MYBATIS_MAPPER_SYSTEM) && !a2.contains(IBATIS_MAPPER_SYSTEM)) break block4;	
            return this.getInputSource(MYBATIS_MAPPER_DTD, publicId, systemId);	
        }	
        return null;	
    }	
}	
	
