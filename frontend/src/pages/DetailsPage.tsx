import { useLocation, useNavigate } from "react-router-dom";

const DetailsPage = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const flight = location.state?.flight;

  if (!flight) {
    return <p>Error: No flight data available.</p>;
  }

  return (
    <div className="details-container">
      <h1>Flight Details</h1>
      <ul className="details-list">
        <li>
          <strong>Departure:</strong> {flight.departureDateTime}
        </li>
        <li>
          <strong>Arrival:</strong> {flight.arrivalDateTime}
        </li>
        <li>
          <strong>From:</strong> {flight.departureAirport} (
          {flight.departureAirportCode})
        </li>
        <li>
          <strong>To:</strong> {flight.arrivalAirport} (
          {flight.arrivalAirportCode})
        </li>
        <li>
          <strong>Airline:</strong> {flight.airlineName} ({flight.airlineCode})
        </li>
        <li>
          <strong>Duration:</strong> {flight.totalFlightDuration}
        </li>
        <li>
          <strong>Base Price:</strong> {flight.basePrice} {flight.currency}
        </li>
        <li>
          <strong>Fees:</strong> {flight.fees} {flight.currency}
        </li>
        <li>
          <strong>Total Price:</strong> {flight.totalPrice} {flight.currency}
        </li>
      </ul>

      <h3>Flight Segments:</h3>
      {flight.segments.map((segment: any, index: number) => (
        <div key={index} className="segment">
          <p>
            <strong>
              {segment.departureDateTime} → {segment.arrivalDateTime}
            </strong>
          </p>
          <p>
            {segment.departureAirport} ({segment.departureAirportCode}) →{" "}
            {segment.arrivalAirport} ({segment.arrivalAirportCode})
          </p>
          <p>
            Airline: {segment.airlineName} ({segment.airlineCode})
          </p>
          <p>Flight Number: {segment.flightNumber}</p>
          <p>Aircraft Type: {segment.aircraftType}</p>
          {segment.layoverDuration && (
            <p>Layover Duration: {segment.layoverDuration}</p>
          )}
        </div>
      ))}

      <button className="return-button" onClick={() => navigate("/results")}>
        Return to Results! ↖️
      </button>
    </div>
  );
};

export default DetailsPage;
