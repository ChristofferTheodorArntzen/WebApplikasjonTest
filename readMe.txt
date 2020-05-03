

Used this to create a project.

This set up the b\


mvn archetype:generate
-DarchetypeArtifactId=jersey-quickstart-grizzly2
-DarchetypeGroupId=org.glassfish.jersey.archetypes
-DinteractiveMode=false
-DgroupId=com.example
-DartifactId=simple-service
-Dpackage=com.example-DarchetypeVersion=2.30.1



------------------------------------------------------------------------------------------------------------------------

Tanker rundt oppgaven
    - En pakke per 'system' eller rad i 'Overordnet flyt' - diagrammet.

    - Lage en User resource, Agreement resource, letter resource.

    - Klient skal bare gjøre et kall til  integrasjonslaget og integrasjonslaget skal gjøre etterkommende kall 'alene'.

   "Konsekvensen av at kallet mot fagsystemet "Oppdater status til avtale sendt" feiler,
    vil være at avtalen ikke blir aktivert noe som resulterer i en mismatch mellom det som informeres
    kunde og status på avtalen i fagsystemet."

    - Her kan man sjekke status på


------------------------------------------------------------------------------------------------------------------------

Tidsbruk på oppgaven

    - ca. 5 timer på å sette opp dette prosjektet og å lage/ teste ut JAX-RS kall i mappestrukturen com.testProject

    - 28.04 - 19:00 Startet på å mocke opp alle klasser



------------------------------------------------------------------------------------------------------------------------


Ressurser brukt for å løse oppgaven
https://howtodoinjava.com/jersey/jersey-restful-client-examples/

PUT vs PATCH
https://www.baeldung.com/http-put-patch-difference-spring
http://jsonpatch.com/
https://stackoverflow.com/questions/47921841/rest-api-update-single-field-of-resource

General info of PUT, POST, PATCH, GET and DELETE
https://restfulapi.net/http-methods/
https://www.sitepoint.com/developers-rest-api/

Setup av Jersey JAX-RS kall, POM og WEB-INF/ web.xml filer

- https://stackoverflow.com/questions/31992461/how-to-run-jersey-server-webservice-server-without-using-tomcat
    Fulgte dette stackOverflow innlegget for å sette opp prosjektet.

https://examples.javacodegeeks.com/enterprise-java/rest/jersey/json-example-with-jersey-jackson/


