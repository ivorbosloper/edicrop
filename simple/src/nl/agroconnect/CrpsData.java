package nl.agroconnect;

import static nl.agroconnect.Util.code;
import static nl.agroconnect.Util.id;
import static nl.agroconnect.Util.nl;
import static nl.agroconnect.Util.polygon;

import java.math.BigDecimal;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import nl.agroconnect.wsEdiCrop.v4_0.CodeType;
import nl.agroconnect.wsEdiCrop.v4_0.EdiCropVersionType;
import nl.agroconnect.wsEdiCrop.v4_0.GetCroppingSchemeRequest;
import nl.agroconnect.wsEdiCrop.v4_0.IDType;
import nl.agroconnect.wsEdiCrop.v4_0.ISOTwoletterCountryCodeIdentifierContentType;
import nl.agroconnect.wsEdiCrop.v4_0.MessageTypeType;
import nl.agroconnect.wsEdiCrop.v4_0.YesNoType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.CropFieldType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.CroppingSchemeMessageType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.DocumentType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.FarmClassificationType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.FarmType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.FieldType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.MeasureType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.SpecifiedPartyType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.StructuredAddressType;

public class CrpsData {
    static final ISOTwoletterCountryCodeIdentifierContentType NL = ISOTwoletterCountryCodeIdentifierContentType.NL;
    
    static MeasureType ha(Number val) {
    	return new MeasureType(BigDecimal.valueOf(15.5146), "ha");  	
    }
    
    static SpecifiedPartyType bms = new SpecifiedPartyType()
		.withID(id("CROPR"))
		.withName(nl("Crop-R"))
		.withCountry(NL)
		.withPostalAddress(
				new StructuredAddressType()
	        		.withStreetName(nl("Helperpark 304"))
	        		.withPostcodeCode(new CodeType().withValue("9723ZA"))
	        		.withCityName(nl("Groningen"))
	        		.withCountry(NL).withCountryName(nl("Nederland"))
	        	);

	static SpecifiedPartyType receiver = new SpecifiedPartyType()
		.withID(id("AGROCONNECT"))
		.withName(nl("Agroconnect test lab"))
		.withCountry(NL)
		.withPostalAddress(
				new StructuredAddressType()
	        		.withStreetName(nl("Droevendaalsesteeg 2"))
	        		.withPostcodeCode(new CodeType().withValue("6708PB"))
	        		.withBuildingName(nl("WUR-gebouwnr. 102"))
	        		.withCityName(nl("Wageningen"))
	        		.withCountry(NL).withCountryName(nl("Nederland"))
	        	);
	
	static SpecifiedPartyType farmContactWithId(IDType farmID) {
		SpecifiedPartyType farmContact = 
				new SpecifiedPartyType()
					.withName(nl("Demo Farm"))
					.withCountry(NL)
					.withPostalAddress(
							new StructuredAddressType()
				        		.withStreetName(nl("Valom"))
				        		.withPostcodeCode(code("9981VD"))
				        		.withCityName(nl("Uithuizen"))
				        		.withCountry(NL).withCountryName(nl("Nederland"))
							);
		return farmContact.withID(farmID);
	}

    /**
     * Convert WKT ring to JAXB serializable formqt
     * @param wktRing WKT description of a ring,  e.g. (30 10, 10 20, 20 40, 40 40, 30 10)
     * @return ring converted to AbstractRingPropertyType
    static AbstractRingPropertyType abstractRing(String wktRing) {
    	String posList = wktRing.substring(1,wktRing.length()-1).replace(",", "");
		AbstractRingPropertyType ring = new AbstractRingPropertyType(new LinearRingType(new DirectPositionListType().withValue(posList).withSrsName("EPSG:4326")));
		return ring;
    }
    
    /**
     * Convert WKT Polygon to JAXB serializable format
     * @param wktPolygon WKT description of the Polygon, e.g. POLYGON ((30 10, 10 20, 20 40, 40 40, 30 10))
     * @return polygon 
	static PolygonType polygon(String wktPolygon) {
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
	*/
	
	static void addFieldsTo(List<FieldType> fields) {
		CropFieldType cropField1 = new CropFieldType()
			.withCropFieldID(id("AGRONL12345777CFDCROPR2013TRW671"))
			.withCropFieldDesignator("Oostwoldjerweg")
			.withBeginDate(new GregorianCalendar(2013,0,1).getTime())
			.withEndDate(new GregorianCalendar(2013,11,31).getTime())
			.withArea(ha(15.5146))
			.withBorder(polygon("POLYGON ((6.8903147161777039 53.2450738337575444, 6.8903492698400530 53.2451531605323538, 6.8903735291182908 53.2452690295511033, 6.8903665549137427 53.2454801820336812, 6.8903906732345312 53.2455093567498210, 6.8904317707948834 53.2455279203204768, 6.8907970842644692 53.2455632202498705, 6.8919946176124487 53.2456418480731841, 6.8923942104446025 53.2456663149181324, 6.8928509767613759 53.2456727281989899, 6.8930342478458595 53.2456669507585332, 6.8933176615714498 53.2456521167541084, 6.8933567775362814 53.2451596855452181, 6.8933643249908547 53.2450646178548723, 6.8933745418251737 53.2450263498121430, 6.8935741377588942 53.2450325083110343, 6.8940284742591711 53.2450528143754696, 6.8940030317662213 53.2453516228772514, 6.8939811418673713 53.2456088399821681, 6.8942245914950515 53.2455809263896853, 6.8944213308508484 53.2455472453629213, 6.8948174907235131 53.2454729184422888, 6.8951186915820699 53.2454101816304828, 6.8953591023713336 53.2453558017865163, 6.8953674965020699 53.2452928601257653, 6.8954434161432738 53.2447236500112311, 6.8955574238171282 53.2439221433559311, 6.8955658627499083 53.2438609275643771, 6.8956774056110151 53.2430266956724267, 6.8957666663094708 53.2424386507317280, 6.8958517035786189 53.2418125187640143, 6.8959423208993433 53.2411811127003176, 6.8960101461510863 53.2406829269848103, 6.8959521219170714 53.2406965256851592, 6.8954008680147858 53.2408369618622714, 6.8907018962487356 53.2421216584070791, 6.8906010631289121 53.2429237036554994, 6.8905125752888239 53.2435359984148917, 6.8904325156071229 53.2441412687124895, 6.8903561875648425 53.2447707548643407, 6.8903147161777039 53.2450738337575444))"))
			.withCountry(NL)
			.withRegulatorySoiltypeCode(new CodeType().withListID("CL021").withValue("KZ"))
			.withCropTypeCode(new CodeType().withListID("CL263").withValue("1020107")) // tarwe
			.withVarietyCode(new CodeType().withListID("CL232").withValue("14407")) // Julius
			.withCropProductionPurposeCode(new CodeType().withListID("CL251").withValue("102"));
			// skipped productionTypeCode, cropProductionSequence, cropProductionPeriodCode, precedingCropField

		CropFieldType cropField2 = new CropFieldType()
			.withCropFieldID(id("AGRONL12345777CFDCROPR2013TRW672"))
			.withCropFieldDesignator("Oostwoldjerweg2")
			.withBeginDate(new GregorianCalendar(2013,0,1).getTime())
			.withEndDate(new GregorianCalendar(2013,11,31).getTime())
			.withArea(ha(15.5146))
			.withBorder(polygon("POLYGON ((6.8903147161777039 53.2450738337575444, 6.8903492698400530 53.2451531605323538, 6.8903735291182908 53.2452690295511033, 6.8903665549137427 53.2454801820336812, 6.8903906732345312 53.2455093567498210, 6.8904317707948834 53.2455279203204768, 6.8907970842644692 53.2455632202498705, 6.8919946176124487 53.2456418480731841, 6.8923942104446025 53.2456663149181324, 6.8928509767613759 53.2456727281989899, 6.8930342478458595 53.2456669507585332, 6.8933176615714498 53.2456521167541084, 6.8933567775362814 53.2451596855452181, 6.8933643249908547 53.2450646178548723, 6.8933745418251737 53.2450263498121430, 6.8935741377588942 53.2450325083110343, 6.8940284742591711 53.2450528143754696, 6.8940030317662213 53.2453516228772514, 6.8939811418673713 53.2456088399821681, 6.8942245914950515 53.2455809263896853, 6.8944213308508484 53.2455472453629213, 6.8948174907235131 53.2454729184422888, 6.8951186915820699 53.2454101816304828, 6.8953591023713336 53.2453558017865163, 6.8953674965020699 53.2452928601257653, 6.8954434161432738 53.2447236500112311, 6.8955574238171282 53.2439221433559311, 6.8955658627499083 53.2438609275643771, 6.8956774056110151 53.2430266956724267, 6.8957666663094708 53.2424386507317280, 6.8958517035786189 53.2418125187640143, 6.8959423208993433 53.2411811127003176, 6.8960101461510863 53.2406829269848103, 6.8959521219170714 53.2406965256851592, 6.8954008680147858 53.2408369618622714, 6.8907018962487356 53.2421216584070791, 6.8906010631289121 53.2429237036554994, 6.8905125752888239 53.2435359984148917, 6.8904325156071229 53.2441412687124895, 6.8903561875648425 53.2447707548643407, 6.8903147161777039 53.2450738337575444))"))
			.withCountry(NL)
			.withRegulatorySoiltypeCode(new CodeType().withListID("CL021").withValue("KZ"))
			.withCropTypeCode(new CodeType().withListID("CL263").withValue("1010101")) // Aardappelen
			.withVarietyCode(new CodeType().withListID("CL232").withValue("10121")) // Agria
			.withCropProductionPurposeCode(new CodeType().withListID("CL251").withValue("001")); // Friet
		;
		
		CropFieldType cropField3 = new CropFieldType()
			.withCropFieldID(id("AGRONL12345777CFDCROPR2013TRW674"))
			.withCropFieldDesignator("Oostwoldjerweg3")
			.withBeginDate(new GregorianCalendar(2013,0,1).getTime())
			.withEndDate(new GregorianCalendar(2013,11,31).getTime())
			.withArea(ha(7.4003))
			.withBorder(polygon("POLYGON ((6.8953674965020699 53.2452928601257653, 6.8953591023719785 53.2453558017855499, 6.8956461848141624 53.2452908508008349, 6.8958221007374911 53.2452400927079736, 6.8961247373392025 53.2451357258414930, 6.8962641005539638 53.2450992486376293, 6.8968276681480347 53.2449517611604008, 6.8972360292128982 53.2448431766325641, 6.8975950327967421 53.2447477217089400, 6.8982712313277057 53.2403342804142028, 6.8978259249044394 53.2403712590739389, 6.8975092813800378 53.2404047206175548, 6.8973005110466623 53.2404333648388786, 6.8969436559073154 53.2404868144665002, 6.8969352667151416 53.2404881413136550, 6.8968510621546182 53.2405014315923637, 6.8965970448680300 53.2405497053133629, 6.8962100425614494 53.2406360639007019, 6.8960101461517267 53.2406829269838369, 6.8959423208993433 53.2411811127003176, 6.8958517035786189 53.2418125187640143, 6.8957666663094717 53.2424386507317280, 6.8956774056110159 53.2430266956724196, 6.8955658627499083 53.2438609275643771, 6.8955574238171282 53.2439221433559311, 6.8954434161432738 53.2447236500112311, 6.8953674965020699 53.2452928601257653))"))
			.withCountry(NL)
			.withRegulatorySoiltypeCode(new CodeType().withListID("CL021").withValue("KZ"))
			.withCropTypeCode(new CodeType().withListID("CL263").withValue("1010201")) // Suikerbiet
			.withVarietyCode(new CodeType().withListID("CL232").withValue("15194")) // Magnolia
			.withCropProductionPurposeCode(new CodeType().withListID("CL251").withValue("166")); // Industrie
		;
		
		FieldType field1 = new FieldType()
			.withFieldID(id("AGRONL12345777FDCROPR2013TRW2125"))
			.withFieldDesignator("Huis")
			.withBeginDate(new GregorianCalendar(2012,0,1).getTime())
			.withEndDate(new GregorianCalendar(9999,11,31).getTime())
			.withArea(ha(22.2823))
			.withThirdPartyFieldID(new IDType().withValue("1120801").withSchemeName("AAN").withSchemeAgencyName("MINLNV"))
			// skipping: samplingProtocol, pal, pw, aboveAveragePotatoes, aboveAverageSugarbeet
			.withBorder(polygon("POLYGON ((6.8904382400000062 53.2440660299999422, 6.8903267000000268 53.2449043999998679, 6.8903226100001858 53.2449579600002423, 6.8903413200004238 53.2450968200001711, 6.8903796699997795 53.2452306799998993, 6.8903894599996462 53.2453411300001420, 6.8903848900003055 53.2454374899998797, 6.8903939300002941 53.2454694699998399, 6.8904305799999417 53.2454975500002519, 6.8904769800000762 53.2455207399999111, 6.8905606800003536 53.2455339599999746, 6.8911605700001362 53.2455716200002485, 6.8923126299995676 53.2456427800000398, 6.8929752999998497 53.2456618199999170, 6.8932901299992224 53.2456531099999140, 6.8933689300002303 53.2450707600001465, 6.8934096200002797 53.2450440900001709, 6.8940484199998755 53.2450610399997970, 6.8939997600005158 53.2455978399990855, 6.8943348199996830 53.2455437000000416, 6.8947167999997054 53.2454711400002054, 6.8953167000004001 53.2453387700002025, 6.8955489399999905 53.2452918600001794, 6.8956845599995731 53.2452592500000819, 6.8957382099996192 53.2452633299999860, 6.8958031400001154 53.2452494400003289, 6.8958339200000873 53.2452228900001643, 6.8959299199999444 53.2451907799998949, 6.8959981799995971 53.2451590100001440, 6.8965832900000059 53.2450006600000592, 6.8976023599996914 53.2447262900003935, 6.8976272499999292 53.2445880599997707, 6.8977162800002407 53.2439033299997604, 6.8978011699995809 53.2432139000002849, 6.8978882800000916 53.2426445199998923, 6.8979508299996510 53.2422228600000906, 6.8980471500000720 53.2415202100001181, 6.8981473500002739 53.2408721900000614, 6.8982188300001610 53.2403660100009191, 6.8980746400001056 53.2403797199998010, 6.8978074600001182 53.2403902100001005, 6.8977922400001486 53.2404082400000220, 6.8972909500004285 53.2404680399999748, 6.8968417099999666 53.2405426500001298, 6.8964659599995999 53.2406222800000748, 6.8962199699999998 53.2406717399999181, 6.8960160700001412 53.2407349299995900, 6.8959507199999290 53.2410769700001865, 6.8958896900001339 53.2415425900002219, 6.8957805100001766 53.2422751299998467, 6.8956855699997819 53.2430181799999289, 6.8955749000001951 53.2438220699998652, 6.8954484899995769 53.2447426700001714, 6.8953773400002349 53.2452024699997821, 6.8953298800000260 53.2452054499997942, 6.8953902600000161 53.2447778800001217, 6.8955422700000568 53.2436821999999310, 6.8956899099998692 53.2426317400002347, 6.8958407499999499 53.2415598500001579, 6.8959530099994915 53.2407452400011181, 6.8953781899996018 53.2409129600002231, 6.8943908099997762 53.2411845400000203, 6.8930738399997349 53.2415529800000442, 6.8919418999997735 53.2418287300001722, 6.8913545099997364 53.2419775700001665, 6.8907196900006493 53.2421305699997092, 6.8906474200001409 53.2425594699999110, 6.8905450399998482 53.2433192600001632, 6.8904960799995729 53.2436777300000870, 6.8904382400000062 53.2440660299999422))"))
			.withCropField(cropField1, cropField2)

			.withPal(nl("26"))
			.withPw(nl("16"))
			.withSamplingProtocol(nl("Spurway"));
		
		FieldType field2 = new FieldType()
			.withFieldID(id("AGRONL12345777FDCROPR2013TRW2126"))
			.withFieldDesignator("Huis2")
			.withBeginDate(new GregorianCalendar(2012,0,1).getTime())
			.withEndDate(new GregorianCalendar(9999,11,31).getTime())
			.withArea(ha(6.0499	))
			.withThirdPartyFieldID(new IDType().withValue("1349154").withSchemeName("AAN").withSchemeAgencyName("MINLNV"))
			// skipping: samplingProtocol, pal, pw, aboveAveragePotatoes, aboveAverageSugarbeet
			.withBorder(polygon("POLYGON ((6.8990278099997004 53.2443399200000371, 6.8997304799992127 53.2441628100001410, 6.8997559200002261 53.2437615700000393, 6.8999979100004323 53.2420837899998887, 6.9001189799999798 53.2412616100001799, 6.9002509399998511 53.2402488500005688, 6.8982618200009194 53.2403657499998175, 6.8976554100004090 53.2447120899989130, 6.8990278099997004 53.2443399200000371))"))
			.withCropField(cropField3);
		fields.add(field1);
		fields.add(field2);
	}
	
	static CroppingSchemeMessageType message(GetCroppingSchemeRequest payload) {
		SpecifiedPartyType farmContact = farmContactWithId(payload.getFarmID());
    	DocumentType doc = new DocumentType()
				.withID(id(payload.getFarmID().getValue() + "_" + payload.getMessageId()))
				.withType(MessageTypeType.CRPSCH)
				.withEdiCropVersion(EdiCropVersionType.CRP_4_0)
				.withMessageTypeVersion("4.0")
				.withIssueDate(new Date())
				.withCropYear(new BigDecimal(2013))
				.withReportCount(new BigDecimal(0.0))
				.withIssuer(farmContact) 		// de teler die het voorgenomen bouwplan afgeeft (FarmID / KVK)
				.withSender(bms) 			// het systeem van waaruit het bouwplan wordt opgeleverd, bijvoorbeeld Agrovision of Dacom
				.withReceiver(receiver) 	// voor wie het bericht bedoeld is, in dit geval de aanduiding van het adviessysteem
				.withDescription(nl("Voorbeeld CroppingScheme bericht voor testboerderij"));
				
    	FarmType farm = new FarmType()
    		.withFarmContact(farmContact)
    		.withThirdPartyFarmID(new IDType().withSchemeAgencyName("DACOM").withValue("1"))
    		.withFarmClassification(new FarmClassificationType(code("1"), YesNoType.N));
    	addFieldsTo(farm.getField());

    	CroppingSchemeMessageType msg = new CroppingSchemeMessageType(doc, farm);
		return msg;
	}
	
}
