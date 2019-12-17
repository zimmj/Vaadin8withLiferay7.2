# Vaadin8withLiferay7.2
This Repo shows an simple example, how Vaadin 8 can be used with Liferay 7.2. The goal was to integrate all Vaadin related buildtools into the Liferay Workspace, so that we can build the vaadin Portlet with the gradle deploy command. 

# How to Test it byyourself
1. Clone this repo
2. run: gradle initBundle
        a new Liferay bundle is downloaded and prepared
        the directory in configs are copiead to there specific places in the bundles directory
3. run tomcat server
        best is to simple run the startup.bat or startup.sh file. They are located in bundles/tomcat-{{version}}/bin
4. run gradle deploy
        this task is going to build the Vaadin module and deploy it on the server, we just startet
5. go to localhost:8080 and go through the login process
6. add the portlet to the fronside of the new Liferay. It should be under modules/Vaadin Example
        the Portlet should be running on the site

Features:
  - custome Widgetset. You can add your own connector and so to the widgetset
  - custome theme. The theme can be changed as wanted. It inherits from the basic vaadin theme
  - Liferay feature can be used

# Vaadin 8 Compatibility mode
If you want to use the Vaadin 8 Compatibility mode, you need to add all the jars to the server. The vaadin 8 compatiility client can not be run on osgi, as it doesn't have a manifest file. Fortunately it is only needed in compile time. To avoid osgi trouble add following statement to the bnd.bnd file:

Import-Package: \\
        com.vaadin.v7.client.*; resolution:=optional, \\        *
