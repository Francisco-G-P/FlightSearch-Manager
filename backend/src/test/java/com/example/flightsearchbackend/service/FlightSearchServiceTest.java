/*
 * package com.example.flightsearchbackend.service;
 * 
 * import static org.junit.jupiter.api.Assertions.*;
 * import static org.mockito.Mockito.*;
 * 
 * import com.example.flightsearchbackend.dto.FlightSearchRequest;
 * import org.junit.jupiter.api.BeforeEach;
 * import org.junit.jupiter.api.Test;
 * import org.junit.jupiter.api.extension.ExtendWith;
 * import org.mockito.InjectMocks;
 * import org.mockito.Mock;
 * import org.mockito.MockitoAnnotations;
 * import org.mockito.junit.jupiter.MockitoExtension;
 * 
 * import java.util.List;
 * 
 * @ExtendWith(MockitoExtension.class) // Make sure Mockito handles mocks
 * class FlightSearchServiceTest {
 * 
 * @InjectMocks
 * private FlightSearchService flightSearchService;
 * 
 * @Mock
 * private AmadeusService amadeusService;
 * 
 * @BeforeEach
 * void setUp() {
 * MockitoAnnotations.openMocks(this);
 * }
 * 
 * @Test
 * void searchFlights_ShouldReturnFlights_WhenValidRequest() {
 * // Arrange
 * FlightSearchRequest request = new FlightSearchRequest();
 * request.setOrigin("JFK");
 * request.setDestination("LAX");
 * request.setDepartureDate("2025-05-10");
 * 
 * System.out.println("AmadeusService: " + amadeusService); // Check if null
 * when(amadeusService.getAccessToken()).thenReturn("fake_token");
 * 
 * // Act
 * List<?> result = flightSearchService.searchFlights(request);
 * 
 * // Assert
 * assertNotNull(result);
 * }
 * }
 */
