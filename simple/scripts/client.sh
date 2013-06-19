#!/bin/bash

cat resources/example/croppingSchemeRequest.xml | curl -D - -o resources/example/croppingSchemeResponse.xml -X POST -H 'Content-type: text/xml' -H 'SOAPAction: "getCroppingScheme"' -d "@-" http://localhost:8080/edi
