/* import { test, expect } from "@playwright/test";

test("Results Page displays flights after search", async ({ page }) => {
  await page.goto("http://localhost:5173/");

  // Fill data in Search Page
  await page.fill("input[placeholder='Enter city or airport name']", "JFK");
  await page.fill("input[placeholder='Enter airport code']", "LAX");

  // Select departure date
  const today = new Date();
  const departureDate = today.toISOString().split("T")[0]; // Formato YYYY-MM-DD
  await page.fill("input[type='date']", departureDate);

  // Clic "Search Flights! ✈️"
  await page.click("text=Search Flights! ✈️");

  // Wait for Results Page to load
  await page.waitForSelector(".flight-card", { timeout: 10000 });

  // Verify at least one result (flight card)
  const flights = await page.$$(".flight-card");
  expect(flights.length).toBeGreaterThan(0);

  // Verify result contains expected data
  const firstFlight = flights[0];
  await expect(page.locator(".flight-card").first()).toContainText("JFK");
  await expect(page.locator(".flight-card").first()).toContainText("LAX");
});
 */
