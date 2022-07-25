# BankSystem
Project use SpringBoot and Java 8
# Dependencies
spring-boot-starter-data-jpa, spring-boot-starter-web, commons-csv, h2, spring-boot-starter-test, junit-jupiter-params, assertj-core

# Testing
For testing used Postman to create request

##############################
Endpoints - http://localhost:8080/bankSystem/uploadFile
Method - POST
Param - file (cvs)
For testing use file Filetest.csc from resources 
If file uploaded successfully message will be displayed - "Data imported successful"
![test1](https://user-images.githubusercontent.com/70907964/180756913-00e49a5c-967f-4410-9c64-2b63591db740.jpg)

Endpoints - http://localhost:8080/bankSystem/getBalance
Method - POST
Param - bankAccountNumber, dateFrom, dateTo
Before trying to get balance first need to import some data because database is runtime(H2)
If data was found balance will be displayed

Endpoints - http://localhost:8080/bankSystem/exportFile
Method - POST
Param - dateFrom, dateTo
Before trying to get balance first need to import some data because database is runtime(H2)
If data was found bank statements will be displayed
