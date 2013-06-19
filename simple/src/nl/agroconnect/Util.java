package nl.agroconnect;

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

	// "POLYGON ((6.5549314529563700 53.2483273745940409, 6.5552625729569405 53.2494243045946476, 6.5555473729578448 53.2502988645957487, 6.5555822529576098 53.2503329045948703, 6.5556311729571686 53.2503362445956867, 6.5571204729600625 53.2500265745947701, 6.5571361629587726 53.2500134445950977, 6.5565565329587967 53.2482084145945151, 6.5565213729584757 53.2481641945941604, 6.5564343529589060 53.2481650445935486, 6.5549668729579711 53.2483028345944334, 6.5549394429572327 53.2483145245937592, 6.5549314529563700 53.2483273745940409), (6.5550125953073177 53.2483440143568743, 6.5564407864235976 53.2482099132252671, 6.5564744794749554 53.2482095841221010, 6.5564847970235665 53.2482225603064876, 6.5570533131127595 53.2499929907624860, 6.5556259799468313 53.2502897757982154, 6.5556182897352366 53.2502822707949051, 6.5553361865105471 53.2494159963785734, 6.5550125953073177 53.2483440143568743))"
	protected static PolygonType polygon(String wkt) {
        Pattern pattern = Pattern.compile("POLYGON\\s*\\((?:(\\([\\d\\s\\.,]+\\)),?)+\\)\\s*$");
        Matcher m = pattern.matcher(wkt);
        PolygonType p = null;
        if(m.find()) {
        	String boundary = m.group(1); // (23 34, 234.23 234)
        	String posList = boundary.substring(1,boundary.length()-1).replace(",", "");
			AbstractRingPropertyType ring = new AbstractRingPropertyType(new LinearRingType(new DirectPositionListType().withValue(posList).withSrsName("EPSG:4892")));
            p = new PolygonType(ring, null);

//            int count = m.groupCount();
//            for(int i=2;i<count+1;i++){
//                System.out.println(m.group(i));
//            }
        }
		return p;
	}

}
