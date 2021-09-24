# SRUM MANAGEMENT

---

## Introduction

The SRU Management project was built and developed to help the CA in the SRU department manage fresher, internship and
trainer more systematically and easily in the company's SRU department. The system is built as a Java Web Application
that is easy to use and interface suitable.

## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing
purposes. See deployment for notes on how to deploy the project on a live system. Follow and don't skip any steps :
grinning:

## Requirements

* You have
  installed [Eclipse](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2021-09/R/eclipse-inst-jre-win64.exe "Link to Eclipse download page")
  or [IntelliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows "Link to Intellij IDEA download page") to
  build and run website.
* Supported
  only ![alt text](https://img.shields.io/badge/Windows-0078D6?style=for-the-badge&logo=windows&logoColor=white)

## Technologies Used

![alt text](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![alt text](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![alt text](https://img.shields.io/badge/Microsoft_SQL_Server-CC2927?style=for-the-badge&logo=microsoft-sql-server&logoColor=white)
![alt text](https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)
![alt text](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![alt text](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![alt text](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

## Installation

NOTE : **This method only apply for run local**<br/>
**For Eclipse**

1. In Eclipse , choose File ---> Import ---> Project From Git ---> Clone from URI ---> Copy this
   link : https://gitlab.com/ngoctho.t1k54/hn21_fr_java_02_g1.git ---> Next ---> Fill username and passoword if has --->
   Next ---> Next ---> Finish
2. Open main package in this project ---> Main class ---> Right click ---> Run

**For Intellij IDEA**

1. In Intellij IDEA , choose File ---> Open ---> Open project from Version Source Control.
2. Copy this link : https://gitlab.com/ngoctho.t1k54/hn21_fr_java_02_g1.git
   In Eclipse , choose File ---> Import ---> Project From Git ---> Clone from URI ---> \
   Next ---> Fill username and passoword if has ---> Next ---> Next ---> Finish

## Deployment to server

**In this instruction, we only suggest you deploy to Tomcat Server**<br/>
**For Eclipse**

1. Install Apache Tomcat server form [this page](http://tomcat.apache.org/tomcat-8.5-doc/ "Link to Apache Tomcat docs")
2. Deploy web to the server by
   use [this page](https://www.stdio.vn/java/dynamic-web-application-voi-eclipse-helios-va-tomcat-server-NkQFl1 "Deploy to tomcat use Eclipse")

**For Intellij IDEA**<br/>
**Method 1 : Deploy using tomcat**

1. Install Apache Tomcat server form [this page](http://tomcat.apache.org/tomcat-8.5-doc/ "Link to Apache Tomcat docs")
2. After install Tomcat, right click in project and click Run Maven ---> Plugins ---> maven war plugin ---> war:war
3. Wait to finish
4. After have war file
   , [go here](https://www.codejava.net/servers/tomcat/how-to-deploy-a-java-web-application-on-tomcat) to see tutorial
   how to deploy war file to server

**Method 2 : Deploy using docker**

1. Install Apache Tomcat server form [this page](http://tomcat.apache.org/tomcat-8.5-doc/ "Link to Apache Tomcat docs")
2. Access [this page](https://www.jetbrains.com/help/idea/deploying-a-web-app-into-an-app-server-container.html) to run
   website on server

## Authors

* Nguyen Ngoc Tho
* Nguyen Tuan Tu
* Giang The VU
* Le Huy Cuong
* Le Trung Sang

