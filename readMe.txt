------------------------------------------------------------------------------------------------------------------------

Tanker rundt oppgaven
    - En pakke per 'system' eller rad i 'Overordnet flyt' - diagrammet.

    - Lage en User resource, insurance resource, letter resource og et integrationLayer.

    - Klient skal bare gjøre et kall til integrasjonslaget og integrasjonslaget skal gjøre etterkommende kall 'alene'.
        - Foreløpig gjort ved å lage en client i integrasjonslaget og spørre videre med den klienten,
         det må være en bedre måte å gjøre dette på?

    - Lage en mock database som inneholder tre maps med <Integer, Object> for å "lagre" entitene som blir opprettet av
      de forskjellige ressursene, integer var til tenkt PK, kunne vært bedre impl.
      Database objektet må ha et statisk kall for å opprette seg selv så samme state forblir i alle klassene (singleton)



------------------------------------------------------------------------------------------------------------------------

Tidsbruk på oppgaven

    - ca. 5 timer på å sette opp dette prosjektet og å lage/ teste ut JAX-RS kall i mappestrukturen com.testProject

    - 28.04 - 19:00 - 20:00 Startet på å mocke opp alle klasser
    - 01.05 - ca. 2 timer - Laget ferdig fagsystem - klassen
    - 03.05 - ca. 4 timer - Fullførte letterService la til selvstendige tester
                          - Fullførte integrationLayer samt en enkel test som kjører gjennom alle metodene.
    - 03.05 - ca. 2 timer - Splittet opp fagsystem til Insurance- og UserResource og fjernet primitive reponse
                            for kall til disse resourcene fra integrationLayeret.

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
https://stackoverflow.com/questions/29503204/getting-error-error-reading-entity-from-input-stream
https://www.logicbig.com/tutorials/java-ee-tutorial/jax-rs/post-example.html
https://www.baeldung.com/jax-rs-spec-and-implementations
https://stackoverflow.com/questions/107390/whats-the-difference-between-a-post-and-a-put-http-request
https://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html#sec9.1.2
https://stackoverflow.com/questions/8277409/jax-rs-with-embedded-server/8277705#8277705
https://www.vogella.com/tutorials/REST/article.html#jerseyprojectsetup_gradle
https://www.javatpoint.com/jax-rs-example-jersey
