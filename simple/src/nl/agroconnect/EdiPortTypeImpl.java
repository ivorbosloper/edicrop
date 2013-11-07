package nl.agroconnect;

import static nl.agroconnect.Util.id;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
import nl.agroconnect.wsEdiCrop.v4_0.PutCropRecordingRequest;
import nl.agroconnect.wsEdiCrop.v4_0.PutCropRecordingResponse;
import nl.agroconnect.wsEdiCrop.v4_0.PutCroppingAdviceRequest;
import nl.agroconnect.wsEdiCrop.v4_0.PutCroppingAdviceResponse;
import nl.agroconnect.wsEdiCrop.v4_0.crps.CroppingSchemeMessageType;

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
    	CroppingSchemeMessageType msg = CrpsData.message(payload);
    	return new GetCroppingSchemeResponse(0, payload.getMessageId(), new BigDecimal(2013), msg);
    }
	
    public GetCropRecordingListResponse getCropRecordingList(GetCropRecordingListRequest payload) { 
		List<IDType> farms = new ArrayList<IDType>();
		farms.add(id("1"));
		return new GetCropRecordingListResponse(0, payload.getMessageId(), new BigDecimal(2013), farms);
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
