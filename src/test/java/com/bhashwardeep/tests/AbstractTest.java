package com.bhashwardeep.tests;
import com.bhashwardeep.listener.Testlistener;
import com.bhashwardeep.util.Config;
import com.bhashwardeep.util.Constants;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;

@Listeners({Testlistener.class})
public class AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void setupConfig(){
        Config.initialize();
    }

    @BeforeTest
   // @Parameters({"browser"})
    public void setDriver(ITestContext context) throws MalformedURLException {
        System.out.println("selenium.grid.enabled = " + System.getProperty("selenium.grid.enabled"));
        System.out.println("Grid mode? " + "true".equalsIgnoreCase(System.getProperty("selenium.grid.enabled")));
        /*if ("true".equalsIgnoreCase(System.getProperty("selenium.grid.enabled"))) {
            this.driver = getRemoteWebDriver();
        } else {
            this.driver = getLocalWebDriver();
        }*/
        this.driver = (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)))? getRemoteWebDriver() : getLocalWebDriver();
        context.setAttribute(Constants.DRIVER, driver);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebDriver getRemoteWebDriver() throws MalformedURLException {
        //http://localhost:4444/wd/hub
        Capabilities capabilities = new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))){
            //  if(browser.equalsIgnoreCase("chrome")){
            capabilities = new FirefoxOptions();
        }

        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        //log.info("grid url: {}", url);
        System.out.println("Grid Url:" + url);
        return new RemoteWebDriver(URI.create(url).toURL(), capabilities);
        //return new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);

    }

    private WebDriver getLocalWebDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    //For viewing test run in Selenium Grid
   /* @AfterMethod
    public void sleep(){
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }*/
}