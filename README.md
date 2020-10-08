# Trader API 

## Building and running
### Docker
With Docker and Make installed all you should have to do to run the tests and then application is:
```
make docker.run
```
To only run the tests you can use:
```
make docker.test
```
To rebuild the project:
```
make docker.build
```
To clean up:
```
make docker.clean
```
To stop everything:
```
make docker.stop
```
### Local
Bring up dependencies:
```
make setup
```
Pull down dependencies:
```
make teardown
```
Build project:
```
make install
```
Clean up project:
```
make clean
```
Run project
```
make run
```