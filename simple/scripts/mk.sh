#!/bin/bash

#TODO: add -wsdlLocation wsdlLocation	

# http://stackoverflow.com/questions/5488061/can-apachecxf-generate-full-constructors-when-using-the-cxf-codegen-plugin-for-m
export CLASSPATH=$CLASSPATH:lib/jaxb2-value-constructor-3.0.jar

wsdl2java -b resources/bind.xml -d src/ -p nl.agroconnect.wsEdiCrop.v4_0 \
  -server -impl \
  -xjc-Xvalue-constructor \
  -p http://www.agroconnect.nl/Portals/10/XSDs/XSDs_EDI_Crop/v4_0/EDI-Crop-CroppingAdvice_v4_0=nl.agroconnect.wsEdiCrop.v4_0.crpa \
  -p http://www.agroconnect.nl/Portals/10/XSDs/XSDs_EDI_Crop/v4_0/EDI-Crop-CropRecording_v4_0=nl.agroconnect.wsEdiCrop.v4_0.crpr \
  -p http://www.agroconnect.nl/Portals/10/XSDs/XSDs_EDI_Crop/v4_0/EDI-Crop-CroppingScheme_v4_0=nl.agroconnect.wsEdiCrop.v4_0.crps \
  resources/WSDL_WS-EDI-Crop.wsdl

