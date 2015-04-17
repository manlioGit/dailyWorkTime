# dailyWorkTime #

## Project setup ##
```sh
$ mkdir -p ~/git/trunk
$ cd ~/git/trunk
$ git clone https://github.com/GmTech/dailyWorkTime.git
$ cd dailyWorkTime
$ ./sbt -java-home /usr/lib/jvm/java-7-oracle
```

## Eclipse setup ##
* generate project file
```sh
> eclipse
```
* import project as 'Projects from git' -> Existing local repository -> ... Import existing projects ...
* be sure to use java7 as JRE System Library

### Javascript/JQuery Support ###
* right click on project -> configure -> Convert to Javascript project
* install jsdt-jquery using [these instructions](code.google.com/a/eclipselabs.org/p/jsdt-jquery/wiki/Installation)
* exclude compile error of minified js files
  * right click on project -> javascript -> include path -> source tab 
  * edit 'Excluded' node and in 'Exclusion pattern' add src/main/webapp/js/*.min.js

## Launch application ##
```sh
> ~;container:start; container:reload /
```
* browse to localhost:8080/

## Useful reading ##
* http://docs.scala-lang.org/tutorials/scala-for-java-programmers.html
* http://scalatra.org/2.4/guides/
* http://eloquentjavascript.net/
* https://rogerdudler.github.io/git-guide/
  * http://git.or.cz/course/svn.html
  * http://www.git-tower.com/blog/git-for-subversion-users-cheat-sheet/
* http://fullcalendar.io/docs/
