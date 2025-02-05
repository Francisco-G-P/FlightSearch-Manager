/* import { test, expect } from "@playwright/test";

test("Search Page allows entering required flight details", async ({ page }) => {
  await page.goto("http://localhost:5173/");

  // Fill departure/arrival airports
  await page.fill("input[placeholder='Enter city or airport name']", "JFK");
  await page.fill("input[placeholder='Enter airport code']", "LAX");

  // Select departure date
  const today = new Date();
  const departureDate = today.toISOString().split("T")[0];
  await page.fill("input[type='date']", departureDate);

  // Clic "Search Flights ! ✈️"
  await page.click("text=Search Flights! ✈️");

  // Wait up to 10s for results to return
  await page.waitForSelector(".flight-card", { timeout: 10000 });

  // Check Results Page loading
  await expect(page).toHaveURL(/.*results/);

  // Check for result
  const flights = await page.$$(".flight-card");
  expect(flights.length).toBeGreaterThan(0);
});
 */
