package nl.agroconnect;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nl.agroconnect.wsEdiCrop.v4_0.CodeType;
import nl.agroconnect.wsEdiCrop.v4_0.IDType;
import nl.agroconnect.wsEdiCrop.v4_0.TextType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.AbstractRingPropertyType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.DirectPositionListType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.LinearRingType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.PolygonType;

public class Util {
    protected static TextType nl(String s) {
    	return new TextType(s, "NL");
    }

    protected static IDType id(String s) {
    	return new IDType().withValue(s);
    }
    
    protected static CodeType code(String s) {
    	return new CodeType().withValue(s);
    }

    /**
     * Convert WKT ring to JAXB serializable formqt
     * @param wktRing WKT description of a ring,  e.g. (30 10, 10 20, 20 40, 40 40, 30 10)
     * @return ring converted to AbstractRingPropertyType
     */
    protected static AbstractRingPropertyType abstractRing(String wktRing) {
    	String posList = wktRing.substring(1,wktRing.length()-1).replace(",", "");
		AbstractRingPropertyType ring = new AbstractRingPropertyType(new LinearRingType(new DirectPositionListType().withValue(posList).withSrsName("EPSG:4326")));
		return ring;
    }
    
    /**
     * Convert WKT Polygon to JAXB serializable format
     * @param wktPolygon WKT description of the Polygon, e.g. POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))
     * @return polygon 
     */
	protected static PolygonType polygon(String wktPolygon) {
        Pattern pattern = Pattern.compile("POLYGON\\s*\\((?:(\\([\\d\\s\\.,]+\\)),?)+\\)\\s*$");
        Matcher m = pattern.matcher(wktPolygon);
        PolygonType p = null;
        if(m.find()) {
            List<AbstractRingPropertyType> interior = null;
        	for(int i=2;i<m.groupCount()+1;i++){
        		if (interior==null) interior = new ArrayList<AbstractRingPropertyType>();
        		interior.add(abstractRing(m.group(i)));
        	}
        	p = new PolygonType(abstractRing(m.group(1)), null);
        }
		return p;
	}
}
