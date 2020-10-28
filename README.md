# employeeserv

## Application Overview
employeeserv is a spring boot rest application which would provide the CRUD operations for `Employee` resource.

There are three modules in this application
- employeeservApi - This module contains the interface.
	- `v1/schema/employee.json` defines the employee resource.
	- `jsonschema2pojo-maven-plugin` is being used to create `Employee POJO` from json file.
	- `EmployeeResource.java` is the interface for CRUD operations on `Employee` resource.
		- GET `/v1/bfs/employees/{id}` endpoint is defined to fetch the resource.
- employeeservImplementation - This module contains the implementation for the rest endpoints.
	- `EmployeeResourceImpl.java` implements the `EmployeeResource` interface.
- employeeservFunctionalTests - This module would have the functional tests.

## How to run the application
- Please have Maven version `3.3.3` & Java 8 on your system.
- Use command `mvn clean install` to build the project.
- Use command `mvn spring-boot:run` from `employeeservImplementation` folder to run the project.
- Use postman or curl to access `http://localhost:8080/v1/bfs/employees/1` GET endpoint. It will return an Employee resource.

## Assignment
We would like you to enhance the existing project and see you complete the following requirements:

- `employee.json` has only `name`, and `id` elements. Please add `date of birth` and `address` elements to the `Employee` resource. Address will have `line1`, `line2`, `city`, `state`, `country` and `zip_code` elements. `line2` is an optional element.
- Add one more operation in `EmployeeResource` to create an employee. `EmployeeResource` will have two operations, one to create, and another to retrieve the employee resource.
- Implement create and retrieve operations in `EmployeeResourceImpl.java`.
- Resource created using create endpoint should be retrieved using retrieve/get endpoint.
- Please add the unit tests to validate your implementation.
- Please use h2 in-memory database or any other in-memory database to persist the `Employee` resource. Dependency for h2 in-memory database is already added to the parent pom.
- Please make sure the validations are done for the requests.
- Response codes are as per rest guidelines.
- Error handling in case of failures.
- Idempotency logic is implemented to avoid duplicate resource creation.

## Assignment submission
Thank you very much for your time to take this test. Please upload this complete solution in Github and send us the link to `bfs-sor-interview@paypal.com`.

##Raghu's Solution
1) Used `jsonschema2pojo-maven-plugin` to add extra fields like `date of birth` and `address` with  `line1`, `line2`, `city`, `state`, `country` and `zip_code` elements.
   Assumed all fields all mandatory and made line2 as optional.
   Modified the POJO's to be compatible with H2 and JPA. You can find them in the package `com.paypal.bfs.test.employeeserv.api.modified`
2) Added new operation to save employee
   @RequestMapping(value = "/v1/bfs/employees", method = RequestMethod.POST)
3) Able to create new employee and the same employee is retrieved from the employeeGetById GET method.
4) Added tests. Please check the module `employeeservFunctionalTests` at `src/test/java`
5) Persisted using H2 in-memory database
6) Validated the employee details payload and requests.
7) Please check exception handling in the module `employeeservApi` at the package `com.paypal.bfs.test.employeeserv.exception`

Postman results
---------------
1)To get employee id

GET
localhost:8080/v1/bfs/employees/1

Response status: 200 OK
Body:
{
    "id": 1,
    "first_name": "BFS",
    "last_name": "Developer",
    "date_of_birth": "19890805",
    "Address": {
        "line1": "12349 Metric Blvd",
        "city": "Austin",
        "state": "TX",
        "country": "USA",
        "zip_code": "78758"
    }
}

2) To create new employee
POST
localhost:8080/v1/bfs/employees
Payload:
{
    "first_name": "Test",
    "last_name": "Save",
    "date_of_birth": "19890805",
    "Address": {
        "line1": "12349 Metric Blvd",
        "city": "Austin",
        "state": "TX",
        "country": "USA",
        "zip_code": "78758"
    }
}

Response status: 200 OK
Body:
{
    "id": 3,
    "first_name": "Test",
    "last_name": "Save",
    "date_of_birth": "19890805",
    "Address": {
        "line1": "12349 Metric Blvd",
        "city": "Austin",
        "state": "TX",
        "country": "USA",
        "zip_code": "78758"
    }
}

3) Bad Request validations
POST
localhost:8080/v1/bfs/employees
Payload:
{
    "first_name": "Test"
}

Response status: 400 Bad Request
Body: empty




