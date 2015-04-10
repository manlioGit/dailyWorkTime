# dailyWorkTime #

## Build, Run, Import ##

```sh
$ cd dailyWorkTime
$ # dependencies download, etc.
$ ./sbt 
... 
# generate specific file for eclipse project
> eclipse
...
# import project as 'Existing Project into Workspace'
...
# to launch application with compile polling  
> ~;container:start; container:reload / 
...
# to see browse to [http://localhost:8080/](http://localhost:8080/)

```
