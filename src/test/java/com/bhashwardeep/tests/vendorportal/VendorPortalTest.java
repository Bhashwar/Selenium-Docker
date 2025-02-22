package com.bhashwardeep.tests.vendorportal;

import com.bhashwardeep.pages.vendorPortal.DashboardPage;
import com.bhashwardeep.pages.vendorPortal.LoginPage;
import com.bhashwardeep.tests.AbstractTest;
import com.bhashwardeep.tests.vendorportal.model.VendorPortalTestData;
import com.bhashwardeep.util.Config;
import com.bhashwardeep.util.Constants;
import com.bhashwardeep.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @Parameters("testDataPath")
    @BeforeTest
    public void setPageObjects(String testDataPath) {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(), testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        //Finance Metrics Validation
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.MonthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.AnnualEarning());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.AvailableInventory());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.ProfitMargin());

        //Order History Validation
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.SearchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}

