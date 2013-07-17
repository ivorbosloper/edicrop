package nl.agroconnect;

import static nl.agroconnect.Util.code;
import static nl.agroconnect.Util.id;
import static nl.agroconnect.Util.nl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import nl.agroconnect.wsEdiCrop.v4_0.EdiCropVersionType;
import nl.agroconnect.wsEdiCrop.v4_0.EdiPortType;
import nl.agroconnect.wsEdiCrop.v4_0.GetCropRecordingListRequest;
import nl.agroconnect.wsEdiCrop.v4_0.GetCropRecordingListResponse;
import nl.agroconnect.wsEdiCrop.v4_0.GetCropRecordingRequest;
import nl.agroconnect.wsEdiCrop.v4_0.GetCropRecordingResponse;
import nl.agroconnect.wsEdiCrop.v4_0.GetCroppingSchemeListRequest;
import nl.agroconnect.wsEdiCrop.v4_0.GetCroppingSchemeListResponse;
import nl.agroconnect.wsEdiCrop.v4_0.GetCroppingSchemeRequest;
import nl.agroconnect.wsEdiCrop.v4_0.GetCroppingSchemeResponse;
import nl.agroconnect.wsEdiCrop.v4_0.IDType;
import nl.agroconnect.wsEdiCrop.v4_0.MessageTypeType;
import nl.agroconnect.wsEdiCrop.v4_0.PutCropRecordingRequest;
import nl.agroconnect.wsEdiCrop.v4_0.PutCropRecordingResponse;
import nl.agroconnect.wsEdiCrop.v4_0.PutCroppingAdviceRequest;
import nl.agroconnect.wsEdiCrop.v4_0.PutCroppingAdviceResponse;
import nl.agroconnect.wsEdiCrop.v4_0.YesNoType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.CroppingSchemeMessageType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.DocumentType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.FarmClassificationType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.FarmType;
import nl.agroconnect.wsEdiCrop.v4_0.crps.SpecifiedPartyType;

@javax.jws.WebService(
                      serviceName = "EdiService",
                      portName = "Edi",
                      targetNamespace = "http://www.agroconnect.nl/Portals/10/XSDs/WS_EDI_Crop/v4_0/",
                      wsdlLocation = "resources/WSDL_WS-EDI-Crop.wsdl",
                      endpointInterface = "nl.agroconnect.wsEdiCrop.v4_0.EdiPortType")
public class EdiPortTypeImpl implements EdiPortType {

    private static final Logger LOG = Logger.getLogger(EdiPortTypeImpl.class.getName());

    public GetCroppingSchemeListResponse getCroppingSchemeList(GetCroppingSchemeListRequest payload) {
		List<IDType> farms = new ArrayList<IDType>();
		farms.add(id("1"));
		return new GetCroppingSchemeListResponse(0, payload.getMessageId(), new BigDecimal(2013), farms);
    }

    public GetCroppingSchemeResponse getCroppingScheme(GetCroppingSchemeRequest payload) { 
    	SpecifiedPartyType farmContact = Data.farmContactWithId(payload.getFarmID());
    	DocumentType doc = new DocumentType()
				.withID(id(payload.getFarmID().getValue() + "_" + payload.getMessageId()))
				.withType(MessageTypeType.CRPSCH)
				.withEdiCropVersion(EdiCropVersionType.CRP_4_0)
				.withMessageTypeVersion("4.0")
				.withIssueDate(new Date())
				.withCropYear(new BigDecimal(2013))
				.withReportCount(new BigDecimal(0.0))
				.withIssuer(farmContact) 		// de teler die het voorgenomen bouwplan afgeeft (FarmID / KVK)
				.withSender(Data.bms) 			// het systeem van waaruit het bouwplan wordt opgeleverd, bijvoorbeeld Agrovision of Dacom
				.withReceiver(Data.receiver) 	// voor wie het bericht bedoeld is, in dit geval de aanduiding van het adviessysteem
				.withDescription(nl("Voorbeeld CroppingScheme bericht voor testboerderij"));
				
    	FarmType farm = new FarmType()
    		.withFarmContact(farmContact)
    		.withThirdPartyFarmID(new IDType().withSchemeAgencyName("DACOM").withValue("1"))
    		.withFarmClassification(new FarmClassificationType(code("1"), YesNoType.N));
    	Data.addFieldsTo(farm.getField());

    	CroppingSchemeMessageType msg = new CroppingSchemeMessageType(doc, farm);
    	return new GetCroppingSchemeResponse(0, payload.getMessageId(), new BigDecimal(2013), msg);
    }
	
    public GetCropRecordingListResponse getCropRecordingList(GetCropRecordingListRequest payload) { 
        GetCropRecordingListResponse _return = null;
        return _return;
    }

    public GetCropRecordingResponse getCropRecording(GetCropRecordingRequest payload) { 
        GetCropRecordingResponse _return = null;
        return _return;
    }

    public PutCropRecordingResponse putCropRecording(PutCropRecordingRequest payload) { 
        PutCropRecordingResponse _return = null;
        return _return;
    }

    public PutCroppingAdviceResponse putCroppingAdvice(PutCroppingAdviceRequest payload) { 
        PutCroppingAdviceResponse _return = null;
        return _return;
    }

}
