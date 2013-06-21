#!/bin/bash

cat resources/example/croppingSchemeListRequest.xml | curl -D - -o resources/example/croppingSchemeListResponse.xml -X POST -H 'Content-type: text/xml' -H 'SOAPAction: "getCroppingSchemeList"' -d "@-" http://localhost:8080/edi

cat resources/example/croppingSchemeRequest.xml | curl -D - -o resources/example/croppingSchemeResponse.xml -X POST -H 'Content-type: text/xml' -H 'SOAPAction: "getCroppingScheme"' -d "@-" http://localhost:8080/edi
