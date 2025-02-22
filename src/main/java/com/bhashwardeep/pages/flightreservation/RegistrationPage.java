package com.bhashwardeep.pages.flightreservation;

import com.bhashwardeep.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends AbstractPage {

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(name = "street")
    private WebElement streetInput;

    @FindBy(name = "city")
    private WebElement cityInput;

    @FindBy(name = "zip")
    private WebElement zipInput;

    @FindBy(id = "register-btn")
    private WebElement registerButton;

    //@FindBy(id = "inputState")
    //private WebElement stateInput;

    public RegistrationPage(WebDriver driver){
       super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.firstNameInput));
        return firstNameInput.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void enterUserDetails(String firstName, String lastName){
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
    }

    public void enterUserCredentials(String email, String password){
        this.emailInput.sendKeys(email);
        this.passwordInput.sendKeys(password);
    }

    public void enterAddress(String city, String street, String zip){
        this.cityInput.sendKeys(city);
        this.streetInput.sendKeys(street);
        this.zipInput.sendKeys(zip);

    }

  /*  public void enterState(String state){
        Select State = new Select(this.stateInput);
        State.selectByValue(state);
    }*/

    public void register(){
        this.registerButton.click();
    }

}



