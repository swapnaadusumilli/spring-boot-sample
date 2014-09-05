spring-boot-sample
==================

Sample Spring Boot project with Gradle as the build system and Groovy as the main backend language. 
The frontend is AngularJS with Grunt and Bower for building and dependency management, respectively.

Since spring boot does not provide any mechanism to run the application as a daemon, Apache commons daemon was added, 
along with a simple init script. Nowadays a systemd configuration file should be provided, though I have not gotten
that far yet.

Another customization is the substitution of Tomcat for the latest stable version of Jetty. I like Jetty.
