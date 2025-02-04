import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const SearchPage = () => {
  const navigate = useNavigate();

  const [departureQuery, setDepartureQuery] = useState("");
  const [departureOptions, setDepartureOptions] = useState([]);
  const [departure, setDeparture] = useState("");
  const [arrivalQuery, setArrivalQuery] = useState("");
  const [arrivalOptions, setArrivalOptions] = useState([]);
  const [arrival, setArrival] = useState("");
  const [departureDate, setDepartureDate] = useState("");
  const [returnDate, setReturnDate] = useState("");
  const [adults, setAdults] = useState(1);
  const [currency, setCurrency] = useState("USD");
  const [nonStop, setNonStop] = useState(false);
  const [errorMessage, setErrorMessage] = useState(""); // Several validations

  useEffect(() => {
    const fetchAirports = async () => {
      if (departureQuery.length < 5) {
        setDepartureOptions([]);
        return;
      }
      try {
        const response = await fetch(
          `http://localhost:8080/api/airports?query=${departureQuery}`
        );
        if (!response.ok) throw new Error("Failed to fetch airports.");
        const data = await response.json();
        setDepartureOptions(data);
      } catch (error) {
        console.error("Error fetching airports:", error);
      }
    };

    fetchAirports();
  }, [departureQuery]);

  useEffect(() => {
    const fetchAirports = async () => {
      if (arrivalQuery.length < 3) {
        setArrivalOptions([]);
        return;
      }
      try {
        const response = await fetch(
          `http://localhost:8080/api/airports?query=${arrivalQuery}`
        );
        if (!response.ok) throw new Error("Failed to fetch airports.");
        const data = await response.json();
        setArrivalOptions(data);
      } catch (error) {
        console.error("Error fetching airports:", error);
      }
    };

    fetchAirports();
  }, [arrivalQuery]);

  const handleSearch = async () => {
    // Departure Airport, Arrival Airport, and Deprature Date can't be empty
    if (!departure || !arrival || !departureDate) {
      setErrorMessage("Please fill in the information needed marked with *");
      return;
    }

    setErrorMessage(""); // Cleans error message if good request

    const searchParams = {
      origin: departure,
      destination: arrival,
      departureDate,
      returnDate: returnDate || null,
      adults,
      currency,
      nonStop,
    };

    try {
      const response = await fetch("http://localhost:8080/api/flights", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(searchParams),
      });

      if (!response.ok) {
        throw new Error("Failed to fetch flight data.");
      }

      const data = await response.json();
      console.log("Flight Results:", data); // Print flights in console

      // Redirect to Results Page with obtained data
      navigate("/results", { state: { flights: data } });
    } catch (error) {
      console.error("Error fetching flights:", error);
    }
  };

  return (
    <div className="search-container">
      <h1>Onto your next adventure! ✈️</h1>
      {errorMessage && <p className="error-message">{errorMessage}</p>}
      <div className="form-group">
        <label>* Departure Airport:</label>
        <input
          type="text"
          value={departureQuery}
          onChange={(e) => setDepartureQuery(e.target.value)}
          placeholder="Enter city or airport name"
        />
        {departureOptions.length > 0 && (
          <ul className="dropdown">
            {departureOptions.map((airport: any) => (
              <li
                key={airport.iataCode}
                onClick={() => {
                  setDeparture(airport.iataCode); // Saves IATA code
                  setDepartureQuery(`${airport.name} ${airport.iataCode}`); // Shows departure airport name
                  setDepartureOptions([]); // Shuts dropdown
                }}
              >
                {airport.name} ({airport.iataCode})
              </li>
            ))}
          </ul>
        )}
      </div>
      <div className="form-group">
        <label>* Arrival Airport:</label>
        <input
          type="text"
          value={arrivalQuery}
          onChange={(e) => setArrivalQuery(e.target.value)}
          placeholder="Enter city or airport name"
        />
        {arrivalOptions.length > 0 && (
          <ul className="dropdown">
            {arrivalOptions.map((airport: any) => (
              <li
                key={airport.iataCode}
                onClick={() => {
                  setArrival(airport.iataCode); // Saves IATA code
                  setArrivalQuery(`${airport.name} ${airport.iataCode}`); // Shows arrival airport name
                  setArrivalOptions([]); // Shuts dropdown
                }}
              >
                {airport.name} ({airport.iataCode})
              </li>
            ))}
          </ul>
        )}
      </div>
      <div className="form-group">
        <label>* Departure Date:</label>
        <input
          type="date"
          value={departureDate}
          onChange={(e) => setDepartureDate(e.target.value)}
          min={new Date().toISOString().split("T")[0]}
        />
      </div>
      <div className="form-group">
        <label>Return Date (optional):</label>
        <input
          type="date"
          value={returnDate}
          onChange={(e) => setReturnDate(e.target.value)}
          min={departureDate}
        />
      </div>
      <div className="form-group">
        <label>Number of Adults:</label>
        <input
          type="number"
          value={adults}
          onChange={(e) => setAdults(Math.max(1, Number(e.target.value)))}
          min={1}
        />
      </div>
      <div className="form-group">
        <label>Currency:</label>
        <select value={currency} onChange={(e) => setCurrency(e.target.value)}>
          <option value="USD">USD</option>
          <option value="MXN">MXN</option>
          <option value="EUR">EUR</option>
        </select>
      </div>
      <div className="form-group">
        <label>Non-Stop Only:</label>
        <input
          type="checkbox"
          checked={nonStop}
          onChange={(e) => setNonStop(e.target.checked)}
        />
      </div>
      <button onClick={handleSearch}>Search Flights! ✈️</button>
    </div>
  );
};

export default SearchPage;
