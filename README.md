# DiSyM

A tool for distributed system mapping.


## Vision

* Reads information about distributed systems from different sources, like e. g. "application.properties", "application.yml" or other sources.
* Will work with Spring Boot applications only for the first time.
* It should be easy to logic to process new sources.
* The information will be stored in a relational database but it should be also possible to add other storages.
* There should be a tool, which is able to show the system relations in a graphical way (maybe by an output as PlantUML file).
* It should be possible to define rules for processing the sources (like "a property ending with 'url' is a possible candidate to be evaluated").
* The application should store dependencies and links and usages of other services.
* As the configuration should be done via a script file or something like that.


## How to Build?

### Requirements

* Java 11+
* Maven 3.5.x+

### Build

Call ``mvn clean install`` on CLI. To suppress unit test execution add option ``-Dmaven.test.skip`` (put this in quotes 
("") if you are using PowerShell).


## How to Call?

Once build successfully the application can be started by:

``.\start.bat`` (Windows) or

``./start.sh`` (Linux) (may need to make the file executable before via ``chmod`` command)

Possible parameters:

|Parameter Name|Example|Description|
|------|-------------------|-------|
|confFile|``confFile=a-valid-yaml-file-name.yaml``|Reads the complete configuration for the call from a YAML file (see below)|
|help|``help``|Prints a help page to the console|
|rule|``rule="setting.to.evaluate LOAD GET_CS_ID url CONTAINS"``|Defines a rule to find matching YAML file entries|
|show|``show``|Prints the evaluation result to the console|
|yamlFile|``yamlFile=/home/blubs/prjct/application.yaml``|The name of the YAML file to check|


## Configuration Files

As an alternative for passing parameters via the command line it is possible to create a configuration file and pass 
this to the application.

The file should be in YAML format and should have the information shown below:

```
disym:
  rules:
    - rule1 (string)
    - ruleN (string)
  yamlFile: name-of-the-yaml.file (string)
```


## Developer Hints

* The back end is generated with the Archimedes tooling which could be also downloaded on github/greifentor.


## Rules

To evaluate, if a configuration setting is a potential url setting, rules could be used. A rule is an UPN expression 
which should return a "true" value if the configuration setting is identified as a potential url.

### How Does It Work?

The application could be configured with a set of rules while starting it (see "How to Call?" chapter above). These 
rules will be executed for each configuration setting found in the specific sources of settings. If one or more rule is 
true for a configuration setting it is handled as a potential url setting.

The rules are defined in a UPN syntax which is working with a data stack and a value store. The data stack is used to 
store parameters for command calls temporarily and to provide the results of command calls.

For instance the rule ``'an.url.setting' url CONTAINS`` checks if the string "url" is contained in the string 
"an.url.setting" and would push the result of that check onto the stack (in this static case: Boolean.TRUE).

The stack as well as the value store are able to store object values. The value store does this under an identifier name.

### Commands

#### AND

Runs an and operation for the two topmost elements of the stack. Both must be of type Boolean.

|||
|------|-------------------|
|Syntax|``BooleanValue BooleanValue AND``|
|Example|``true true AND``|
|Stack after operation|``Boolean.TRUE``|

#### CONTAINS

Checks, if the topmost element of the stack is contained by that one which is stored beneath to it.

|||
|------|-------------------|
|Syntax|``string_to_check_for_contains string_to_check_for_being_contained CONTAINS`` 
|Example|``'an.url.setting' url CONTAINS``
|Stack after operation|``Boolean.TRUE``

#### GET_CS_ID

Separated the identifier from a configuration setting object which must be the topmost of the stack.

|||
|------|-------------------|
|Syntax|``GET_CS_ID`
|Example|``"setting.to.evaluate" LOAD GET_CS_ID``
|Stack after operation|``configuration_setting_identifier``
|Note|The configuration setting to check for is stored in the value store with the identifier "setting.to.evaluate".

#### GET_CS_VALUE

Separated the value from a configuration setting object which must be the topmost of the stack.

|||
|------|-------------------|
|Syntax|``GET_CS_VALUE`
|Example|``"setting.to.evaluate" LOAD GET_CS_VALUE``
|Stack after operation|``configuration_setting_value``
|Note|The configuration setting to check for is stored in the value store with the identifier "setting.to.evaluate".

#### LOAD

The commands loads a value from the value store to the top of stack.

|||
|------|-------------------|
|Syntax|``identifier LOAD``|
|Example|``'an.identifier' LOAD``|
|Stack after operation|``value_stored_under_key_an_identifier``|

#### OR

Runs an or operation for the two topmost elements of the stack. Both must be of type Boolean.

|||
|------|-------------------|
|Syntax|``BooleanValue BooleanValue OR``|
|Example|``true true OR``|
|Stack after operation|``Boolean.TRUE``|

#### STORE

The commands moves the second topmost element of the stack to the value store using the topmost value as identifier.
Both information will be removed from the stack.

|||
|------|-------------------|
|Syntax|``value identifier STORE``
|Example|``Boolean.TRUE 'an.identifier' STORE``
|Stack after operation|empty|

### Values

Any token in a rule which is not identified as a command will be stored as a value. While converting the string with the 
rule the application tries to find the correct type for the value. Any value which cannot be identified as an explicit 
type will be stored as a string.

|Type|Values to Pass|
|-|-|
|Boolean|true, false (in all cases)|
|String|bla, "bla, bla", 'bla, bla bla' (use quotes for space containing strings)|