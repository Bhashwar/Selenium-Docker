package com.bhashwardeep.tests.flightreservation;

import com.bhashwardeep.pages.flightreservation.*;
import com.bhashwardeep.tests.AbstractTest;
import com.bhashwardeep.tests.flightreservation.model.flightReservationTestData;
import com.bhashwardeep.util.Config;
import com.bhashwardeep.util.Constants;
import com.bhashwardeep.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private RegistrationPage registrationPage;
    private flightReservationTestData testData;


    @BeforeTest
    @Parameters("testDataPath")
    public void setRegistrationPage(String testDataPath) {
         this.registrationPage = new RegistrationPage(driver);
         this.testData = JsonUtil.getTestData(testDataPath, flightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest() {

        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt(), "RegistrationPage not displayed.");

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.city(), testData.street(), testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt(), "RegistrationConfirmationPage not displayed.");
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
        registrationConfirmationPage.gotoFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest() {
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt(), "FlightsSearchPage not displayed.");
        flightsSearchPage.setPassenger("2");
        flightsSearchPage.setSearchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightSelectionPage() {
        FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
        Assert.assertTrue(flightSelectionPage.isAt(), "FlightSelectionPage not displayed.");
        flightSelectionPage.selectFlights();
        flightSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionPage")
    public void flightConfirmationPage() {
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt(), "FlightConfirmationPage not displayed.");
        System.out.println(flightConfirmationPage.getPrice());
        Assert.assertEquals(flightConfirmationPage.getPrice(), "$1169 USD", "Price mismatch.");
    }

}