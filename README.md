# Read Me First

## *This project contain spring boot, postgresql, liquibase, swagger*
1. clone this project  (```git clone https://github.com/emranhos1/Customer-Processing.git```)
2. clean build this project (```mvn clean install``` OR ```mvn clean package```)
3. Run this project (```mvn spring-boot:run```)
4. after run your can find swagger url here (```http://localhost:8080/api/swagger-ui/index.html#/```)
5. In swagger you find 2 API endpoin under *customer-controller* (```/parse-data``` and ```/export-data```) it looks like bellow
<img width="500" height="200" alt="image" src="https://user-images.githubusercontent.com/20554949/227706728-cefe746b-bc43-4851-b20c-1c7116abb6b8.png">

6.  ```/parse-data``` api parse all 1M data from *1M-customers.txt*  file what are located at ```/src/main/resources/1M-customers.txt```
<img width="350" height="200" alt="image" src="https://user-images.githubusercontent.com/20554949/227707589-bbad53bb-d8a4-452d-ba0d-7e20787ab063.png"> (Then) <img width="350" height="200" alt="image" src="https://user-images.githubusercontent.com/20554949/227707618-5bd78939-bec4-4188-9a99-b7a625d3642b.png">

Follow images and wait for parse response

8.  ```/export-data``` api export all valid customer data from datbase add write them in text files. each file contain 10K data 
9.  All exported files are located at ```/src/main/resources/export/```
10.  You can also find how mane time it takes while parsing data form project console statistics lookes like bellow
<img width="500" height="200" alt="image" src="https://user-images.githubusercontent.com/20554949/227708195-5fdffe13-90b8-456d-ba99-25bcbfab3732.png">
its takes some time 

