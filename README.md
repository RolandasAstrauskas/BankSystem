# BankSystem
Project use SpringBoot and Java 8
# Dependencies
spring-boot-starter-data-jpa, spring-boot-starter-web, commons-csv, h2, spring-boot-starter-test, junit-jupiter-params, assertj-core

# Testing
For testing used Postman to create request
Valide date time format example "27-11-2021 14:00:00"


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
![testing2](https://user-images.githubusercontent.com/70907964/180757335-46c39d80-ff90-47a4-a6cd-c5be75ad0fa7.jpg)

Endpoints - http://localhost:8080/bankSystem/exportFile
Method - POST
Param - dateFrom, dateTo
Before trying to get balance first need to import some data because database is runtime(H2)
If data was found bank statements will be displayed
![testing3](https://user-images.githubusercontent.com/70907964/180757599-cd213704-52a5-4eec-b85f-693df539d2ac.png)
