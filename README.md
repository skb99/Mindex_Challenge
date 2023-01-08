# Coding Challenge Solved
This repository contains the completed Java Coding Challenge by Krishna Bhatia
## Solution
A simple web application has been created and bootstrapped with data. The application contains information about all employees at a company. 

### Task 1
For this task, a new REST endpoint, ReportingStructure, was implemented.  
  This endpoint takes in an employee ID and provides a complete tree of all employees who report under the provided employee (from the ID).
  The endpoint also provides the total number of people reporting under them.

The REST endpoint is a **GET** Method at the following URL:

`http:/localhost:8080/reporting/{id}`

The endpoint returns with a JSON Object.

#### Example 
For the following ReportingStructure visualization
```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```
The number of Rreports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4.
 

To get this, we hit the endpoint by providing John's employeeId as a path Variable in the URL like so.

`http:/localhost:8080/reporting/16a596ae-edd3-4847-99fe-c4518e82c86f` 

We then get the following JSON object in response:
```json
{
    "numberOfReports": 4,
    "employee": {
        "employeeId": "16a596ae-edd3-4847-99fe-c4518e82c86f",
        "firstName": "John",
        "lastName": "Lennon",
        "position": "Development Manager",
        "department": "Engineering",
        "directReports": [
            {
                "employeeId": "b7839309-3348-463b-a7e3-5de1c168beb3",
                "firstName": "Paul",
                "lastName": "McCartney",
                "position": "Developer I",
                "department": "Engineering",
                "directReports": null
            },
            {
                "employeeId": "03aa1462-ffa9-4978-901b-7c001562cf6f",
                "firstName": "Ringo",
                "lastName": "Starr",
                "position": "Developer V",
                "department": "Engineering",
                "directReports": [
                    {
                        "employeeId": "62c1084e-6e34-4630-93fd-9153afb65309",
                        "firstName": "Pete",
                        "lastName": "Best",
                        "position": "Developer II",
                        "department": "Engineering",
                        "directReports": null
                    },
                    {
                        "employeeId": "c0c2293d-16bd-4603-8e08-638a9d18b22c",
                        "firstName": "George",
                        "lastName": "Harrison",
                        "position": "Developer III",
                        "department": "Engineering",
                        "directReports": null
                    }
                ]
            }
        ]
    }
}
```
* The above can endpoint can be used for any employee that has been created, if the employee doesnt have anyone reporting 
to them, the number of reports with default to 0.

In the case of the employee not existing in the company database, the following error message is returned.
```json
{
    "timestamp": "2023-01-07T04:26:45.617+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Provided Employee not found with employeeId:28a596ae-eds3-4847-00fe-c4518e98c86g",
    "path": "/reporting/%28a596ae-eds3-4847-00fe-c4518e98c86g"
}
```

### Task 2
For this task, a new type Compensation was created with the following fields: employee, salary, and effectiveDate.

Along with the new type, 2 new REST endpoints were made as follows:
```text
* CREATE
    * HTTP Method: POST
    * URL: http://localhost:8080/compensation/create
    * PAYLOAD: Compensation
    * RESPONSE: Compensation
* READ
    * HTTP Method: GET
    * URL: http://localhost:8080/compensation/{id}
    * RESPONSE: Compensation
```

#### Example
We run a **POST** Method at the following URL:
`http:/localhost:8080/compensation/create`

Providing the Payload with a JSON object in the following format:
```json
{
  "employee": {
    "employeeId": "7aecb003-7a22-4bd6-95bc-4934d6559833",
    "firstName": "Krishna",
    "lastName": "Bhatia",
    "position": "Software Engineer",
    "department": "Mindex Devs"
  },
  "salary": 3765.88,
  "effectiveDate": "2023-01-07"
}
```
We get the following JSON object in response:
```json
{
  "employee": {
    "employeeId": "7aecb003-7a22-4bd6-95bc-4934d6559833",
    "firstName": "Krishna",
    "lastName": "Bhatia",
    "position": "Software Engineer",
    "department": "Mindex Devs",
    "directReports": []
  },
  "salary": 3765.88,
  "effectiveDate": "2023-01-07T00:00:00.000+0000"
}
```
Now the compensation has been created for this employee.

Now we can hit the READ endpoint (as the compensation exists) by the running a **GET** Method on the
following URL with the above user's employeeId as the path variable:
`http://localhost:8080/compensation/7aecb003-7a22-4bd6-95bc-4934d6559833`

We get the following JSON object in Response:
```json
{
    "employee": {
        "employeeId": "7aecb003-7a22-4bd6-95bc-4934d6559833",
        "firstName": "Krishna",
        "lastName": "Bhatia",
        "position": "Software Engineer",
        "department": "Mindex Devs",
        "directReports": []
    },
    "salary": 3765.88,
    "effectiveDate": "2023-01-07T00:00:00.000+0000"
}
```
In the case of a compensation for an employee not being created before attempting to read it, the following message is returned: 
```json
{
    "timestamp": "2023-01-08T04:30:46.652+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "No Compensation information is found for employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f",
    "path": "/compensation/16a596ae-edd3-4847-99fe-c4518e82c86f"
}
```

#### Testing
Basic Unit testing was conducted for each task with all test cases passed.

#### Validation
Validations were implemented for Employee and Compensation creation to resolve errors due to Null values.






-------------------------------
# Original Challenge 
## What's Provided
A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped 
with data. The application contains information about all employees at a company. On application start-up, an in-memory 
Mongo database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.

### How to Run
The application may be executed by running `gradlew bootRun`.

### How to Use
The following endpoints are available to use:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```
The Employee has a JSON schema of:
```json
{
  "type":"Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
          "type": "string"
    },
    "position": {
          "type": "string"
    },
    "department": {
          "type": "string"
    },
    "directReports": {
      "type": "array",
      "items" : "string"
    }
  }
}
```
For all endpoints that require an "id" in the URL, this is the "employeeId" field.

## What to Implement
Clone or download the repository, do not fork it.

### Task 1
Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of 
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example, 
given the following employee structure:
```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```
The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4. 

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return 
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will 
not be persisted.

### Task 2
Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create 
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the 
Compensation from the persistence layer.

## Delivery
Please upload your results to a publicly accessible Git repo. Free ones are provided by Github and Bitbucket.
