package com.bhashwardeep.pages.flightreservation;

import com.bhashwardeep.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightsSearchPage extends AbstractPage {

    public FlightsSearchPage(WebDriver driver){
        super(driver);
    }
    @FindBy(id = "passengers" )
    private WebElement passengerSelect;

    @FindBy(id = "search-flights")
    private WebElement searchFlightsButton;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengerSelect));
        return passengerSelect.isDisplayed();
    }

    public void setPassenger(String noOfPassengers){
        Select passengers = new Select(this.passengerSelect);
        passengers.selectByValue(noOfPassengers);
    }

    public void setSearchFlights() {
        this.searchFlightsButton.click();
    }
}
