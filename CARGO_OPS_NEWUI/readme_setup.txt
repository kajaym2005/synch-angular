############################################
#Prerequisite							   #
############################################
1) Maven 2.2.1
2) Java version  > "1.6"
3) Install Node JS
4) Run : mvn install:install-file -Dfile=C:\bea103\wlserver_10.3\server\lib\wlclient.jar -DgroupId=weblogic -DartifactId=wlclient -Dversion=10.3 -Dpackaging=jar

   
   http://nodejs.org/
   Note : By default npm folder will get create under C:\Users\thiyagu.selvaraj\AppData\Roaming\npm
   NPM - node package manager
   Add "C:\Users\thiyagu.selvaraj\apps\nodejs" folder to the windows path.

   Current Version: v0.10.30
############################################
4) Install Grunt and Bower
   from command prompt
   
npm install -g grunt-cli bower 
   
   Note : Now You can see "bower" and "grunt-cli" folder under C:\Users\thiyagu.selvaraj\AppData\Roaming\npm\node_modules

############################################
5) Get the latest version of code from
https://bdc6.ams.accenture.com/gerrit/asw/afls/air/CARGO_OPS_NEWUI

############################################

6) Go to C:\projects\cargoops\modules\CARGO_OPS_NEWUI folder 
npm install
bower install

############################################
7) Build the source using maven command

mvn -f C:\projects\cargoops\tag\CARGO_OPS_NEWUI\pom.xml -Dskiptests  -Dmaven.test.skip=true clean install

############################################
8) Load CARGO_OPS_NEWUI modules in eclipse and setup jetty.

cargoops_env_name=thiyagu
cargoops_install_name=demo
cargoops_site_dir=C:\projects\cargoops\tag\CARGO_OPS_NEWUI\src\main\resources\site


http://localhost:8080/cargoopswar2/views/crud/usercrud.jsp

Also you can try without the jetty server.

grunt server


############################################
9) to run the javascript unit and e2e tests

grunt test

