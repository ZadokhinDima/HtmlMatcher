# HtmlMatcher

Small project to help you find specific element from sample HTML file in similar but not equal file.

You can easily extend logic by adding new matchers (find all existing matchers in com.zadokhin.service.matchers.impl package).

To build project with maven execute:
mvn clean package

After artifact created execute:
java -jar target/htmp-parser-1.0-SNAPSHOT-spring-boot.jar sampleHtmlFile similarHtmlFile [id of element in sample file]