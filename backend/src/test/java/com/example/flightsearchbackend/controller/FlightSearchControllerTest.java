/*
 * package com.example.flightsearchbackend.controller;
 * 
 * import static
 * org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
 * import static
 * org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
 * 
 * import com.example.flightsearchbackend.dto.FlightSearchRequest;
 * import com.fasterxml.jackson.databind.ObjectMapper;
 * import org.junit.jupiter.api.Test;
 * import org.springframework.beans.factory.annotation.Autowired;
 * import
 * org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
 * import org.springframework.boot.test.context.SpringBootTest;
 * import org.springframework.http.MediaType;
 * import org.springframework.test.web.servlet.MockMvc;
 * 
 * @SpringBootTest
 * 
 * @AutoConfigureMockMvc
 * class FlightSearchControllerTest {
 * 
 * @Autowired
 * private MockMvc mockMvc;
 * 
 * @Autowired
 * private ObjectMapper objectMapper;
 * 
 * @Test
 * void searchFlights_ShouldReturn200_WhenValidRequest() throws Exception {
 * // Arrange
 * FlightSearchRequest request = new FlightSearchRequest();
 * request.setOrigin("JFK");
 * request.setDestination("LAX");
 * request.setDepartureDate("2025-05-10");
 * 
 * String jsonRequest = objectMapper.writeValueAsString(request);
 * System.out.println("Sending JSON: " + jsonRequest); // Verify sent JSON
 * 
 * try {
 * // Act & Assert
 * mockMvc.perform(post("/api/flights")
 * .contentType(MediaType.APPLICATION_JSON)
 * .content(jsonRequest))
 * .andExpect(status().isOk());
 * } catch (Exception e) {
 * System.out.println("ERROR: " + e.getMessage()); // Print message
 * throw e;
 * }
 * }
 * }
 */
