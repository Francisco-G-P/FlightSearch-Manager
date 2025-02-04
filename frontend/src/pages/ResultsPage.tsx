import { useLocation } from "react-router-dom";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const ResultsPage = () => {
  const location = useLocation();
  const flights = location.state?.flights || [];
  const [currentPage, setCurrentPage] = useState(1);
  const flightsPerPage = 2;
  const navigate = useNavigate();

  // Calculate index of lights
  const indexOfLastFlight = currentPage * flightsPerPage;
  const indexOfFirstFlight = indexOfLastFlight - flightsPerPage;
  const currentFlights = flights.slice(indexOfFirstFlight, indexOfLastFlight);

  const totalPages = Math.ceil(flights.length / flightsPerPage);

  // Change page
  const nextPage = () => {
    if (currentPage < totalPages) {
      setCurrentPage(currentPage + 1);
    }
  };

  const prevPage = () => {
    if (currentPage > 1) {
      setCurrentPage(currentPage - 1);
    }
  };

  return (
    <div className="results-container">
      <h1>Flight Results! 🔍</h1>
      <button className="return-button" onClick={() => navigate("/")}>
        Return to Search! ↖️
      </button>
      {currentFlights.length === 0 ? (
        <p>No flights found.</p>
      ) : (
        <ul>
          {currentFlights.map((flight: any, index: number) => (
            <li key={index} className="flight-card">
              <strong>
                {flight.returnDate
                  ? `${flight.departureDateTime} → ${flight.arrivalDateTime}`
                  : flight.departureDateTime}
              </strong>
              <p>
                {flight.departureAirport} ({flight.departureAirportCode}) →{" "}
                {flight.arrivalAirport} ({flight.arrivalAirportCode})
              </p>
              <p>
                Airline: {flight.airlineName} ({flight.airlineCode})
              </p>
              <p>Duration: {flight.totalFlightDuration}</p>
              <p>
                Price: {flight.totalPrice} {flight.currency}
              </p>
              <button
                className="detail-button"
                onClick={() => navigate("/details", { state: { flight } })}
              >
                View Details! 📋
              </button>
            </li>
          ))}
        </ul>
      )}

      {/* Pagination Update */}
      {totalPages > 1 && (
        <div className="pagination">
          <button onClick={prevPage} disabled={currentPage === 1}>
            ⬅️
          </button>
          <span className="page-number">{currentPage}</span>
          <button onClick={nextPage} disabled={currentPage === totalPages}>
            ➡️
          </button>
        </div>
      )}
    </div>
  );
};

export default ResultsPage;
