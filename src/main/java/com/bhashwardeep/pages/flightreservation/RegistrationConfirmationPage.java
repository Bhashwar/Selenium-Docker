package com.bhashwardeep.pages.flightreservation;

import com.bhashwardeep.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement gotoFlightsSearchButtton;

    @FindBy(css = "#registration-confirmation-section p b")
    private WebElement firstNameElement;


    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.gotoFlightsSearchButtton));
        return gotoFlightsSearchButtton.isDisplayed();
    }

    public String getFirstName(){
        return this.firstNameElement.getText();
    }

    public void gotoFlightsSearch(){
        this.gotoFlightsSearchButtton.click();
    }
}
