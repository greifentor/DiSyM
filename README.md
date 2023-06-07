# DiSyM

A tool for distributed system mapping.


## Vision

* Reads information about distributed systems from different sources, like e. g. "application.properties", "application.yml" or other sources.
* It should be easy to logic to process new sources.
* The information will be stored in a relational database but it should be also possible to add other storages.
* There should be a tool, which is able to show the system relations in a graphical way (maybe by an output as PlantUML file).
* It should be possible to define rules for processing the sources (like "a property ending with 'url' is a possible candidate to be evaluated").
* The application should store dependencies and links and usages of other services.
* As the configuration should be done via a script file.
