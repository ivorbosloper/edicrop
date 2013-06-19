edicrop
=======

Edicrop 4.0 reference implementation in Java

This project is for you if you'd like to implement Agroconnect EDICrop 4.0 messages. Please see http://www.agroconnect.nl/ and become a member. It's a shared resource for more information, semantics and implementation details.

This repository consists of several projects: 
- 'simple' is a command-line generation of a EDICrop implementation, along with a working example service implementation
- 'edicrop-client', 'edicrop-server', 'edicrop-war' belong together as mavenized projects and are modelled after http://www.jroller.com/gmazza/entry/web_service_tutorial

The 'simple' project reference implementation exposes the Crop-R example farm, and allows you to test and validate your own implementation

Getting started full
====================

If you understand maven and WSDL you don't need too much help.

> mvn clean install

see http://www.jroller.com/gmazza/entry/web_service_tutorial for more info and background

getting started simple
======================

- clone this repository: git clone http://github.com/ivorbosloper/edicrop
- run "./scripts/mk.sh" for code generation and compilation, from WSDL to Java. Generated classes go to a special directory.
- run "./scripts/run.sh" to start the standalone base server (example code & implentation)
- run "./scripts/client.sh" to send a example message (make sure run.sh is running)




