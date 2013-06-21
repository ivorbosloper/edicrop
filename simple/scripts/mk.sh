#!/bin/bash

# http://stackoverflow.com/questions/5488061/can-apachecxf-generate-full-constructors-when-using-the-cxf-codegen-plugin-for-m

export CLASSPATH=$CLASSPATH:lib/jaxb2-value-constructor-3.0.jar:lib/jaxb-fluent-api-2.1.8.jar:lib/cxf-tools-common-2.7.5.jar
TARGET=generated/
mkdir -p $TARGET bin

# use -server -impl for more classes
wsdl2java -b resources/bind.xml -d $TARGET -p nl.agroconnect.wsEdiCrop.v4_0 \
  -xjc-Xvalue-constructor \
  -xjc-Xfluent-api \
  -p http://www.agroconnect.nl/Portals/10/XSDs/XSDs_EDI_Crop/v4_0/EDI-Crop-CroppingAdvice_v4_0=nl.agroconnect.wsEdiCrop.v4_0.crpa \
  -p http://www.agroconnect.nl/Portals/10/XSDs/XSDs_EDI_Crop/v4_0/EDI-Crop-CropRecording_v4_0=nl.agroconnect.wsEdiCrop.v4_0.crpr \
  -p http://www.agroconnect.nl/Portals/10/XSDs/XSDs_EDI_Crop/v4_0/EDI-Crop-CroppingScheme_v4_0=nl.agroconnect.wsEdiCrop.v4_0.crps \
  resources/WSDL_WS-EDI-Crop.wsdl

# brew install recode, or apt-get install recode
find $TARGET -name "*.java" -exec recode Latin-1..UTF-8 {} \;
find src $TARGET -name "*.java" |xargs javac -d bin/ 

