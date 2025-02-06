# Flight Search Manager   |   Encora's Spark Program
Repository containing the files, hopes and sweet dreams of the Flight Search Manager Project ;)

## Project Description - Breakable Toy II ~~(Buildable Bug 0.75)~~
The project consists of a flight search manager that has various features, from being able to receive airports names and IATA codes, returning results, as well as showing details of said results.

### Technologies Used
<ins>Backend:</ins>
* Spring Boot
* Gradle
* Java

<ins>Frontend:</ins>
* TypeScript
* React

<ins>API Integration:</ins>
* Amadeus REST API
  * Airport and City Search API (Airport codes)
  * Airline Code Lookup API (Airline details)
  * Flight Offers Search (Flight options)
 
<ins>Docker</ins> (Open platform for developing, shipping, and running applications)
 
### Installation & Setup (IDE's terminal)
Download and Installation of:
* [Gradle](https://gradle.org/install/)
* [Java](https://www.java.com/en/download/help/download_options.html)
* [Node.js](https://nodejs.org/en/download)

<ins>Backend Setup:</ins>
1. Clone the repository:
  ```
  git clone https://github.com/Francisco-G-P/FlightSearch-Manager.git
  cd FlightSearch-Manager/backend
  ```
2. Build and run the backend:
  ```
  ./gradlew bootRun
  ```
3. The backend will run on:
  ```
  http://localhost:8080
  ```

<ins>Frontend Setup:</ins>
1. Navigate to the frontend directory:
  ```
  cd ../frontend
  ```
2. Install dependencies:
  ```
  npm install
  ```
3. Start the development server:
  ```
  npm run dev
  ```
4. The frontend will run on:
  ```
  http://localhost:5173
  ```
  
<ins>Docker Setup:</ins>
* Running Docker Images:
  * Backend:
    ```
    docker run -p 8080:8080 flight-search-backend
    ```
  * Frontend:
    ```
    docker run -p 5173:80 flight-search-frontend
    ```

* Running Docker Compose:
  ```
  docker-compose up --build
  ```

To check access to backend and frontend, respectively, try:
* `http://localhost:8080/api/airports?query=new`
  * Should return a JSON with "new" search related data
* `http://localhost/5173/`
  * Should show main page (Search Page)
 
### Usage
1. Search Page: Enter flight details (origin, destination, date, etc.) and click "Search Flights! ✈️"
2. Results Page: View flight options available and click "View Details! 📋"
3. Details Page: View detailed information about selected flight

### Contributing
If you want to contribute:
1. Fork the repository.
2. Create a new branch:
```
git checkout -b feature-name
```
3. Make your changes and commit:
```
git commit -m 'Add feature'
```
4. Push to your branch:
```
git push origin feature-name
```
5. Open a Pull Request

### Contact
For any inquiries, reach out via email at `francisco.gonzalez@encora.com`
