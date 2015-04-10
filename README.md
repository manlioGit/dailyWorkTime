# dailyWorkTime #

## Build & Run ##

```sh
$ cd dailyWorkTime
$ ./sbt
> container:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.
...
To compile polling use:
```sh
> ...
> ~;container:start; container:reload /
```
