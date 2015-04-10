# dailyWorkTime #

## Build & Run ##

```sh
$ cd dailyWorkTime
$ ./sbt #dependencies download, etc.
... 
> eclipse  # generate specific file for eclipse project. Import as 'Existing Project into Workspace'
> ~;container:start; container:reload / # compile polling, launch jetty. Browse to [http://localhost:8080/](http://localhost:8080/)
```
