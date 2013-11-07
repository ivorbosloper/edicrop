#!/bin/bash

endpoint="http://localhost:8080/edi"
if [ $# -gt 0 ]; then
    endpoint=$1
fi

echo sending resources/example/croppingSchemeListRequest.xml...
cat resources/example/croppingSchemeListRequest.xml
cat resources/example/croppingSchemeListRequest.xml | curl -D - -o resources/example/croppingSchemeListResponse.xml -X POST -H 'Content-type: text/xml' -H 'SOAPAction: "getCroppingSchemeList"' -d "@-" $endpoint
echo received:
cat resources/example/croppingSchemeListResponse.xml

echo
echo sending resources/example/croppingSchemeRequest.xml...
cat resources/example/croppingSchemeRequest.xml
cat resources/example/croppingSchemeRequest.xml | curl -D - -o resources/example/croppingSchemeResponse.xml -X POST -H 'Content-type: text/xml' -H 'SOAPAction: "getCroppingScheme"' -d "@-" $endpoint
echo received:
cat resources/example/croppingSchemeResponse.xml
