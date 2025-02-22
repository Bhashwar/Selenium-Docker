package com.bhashwardeep.util;

import com.bhashwardeep.tests.vendorportal.VendorPortalTest;
import com.bhashwardeep.tests.vendorportal.model.VendorPortalTestData;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Demo {

    public static void main(String[] args) throws IOException {

        VendorPortalTestData testData = (VendorPortalTestData) JsonUtil.getTestData("test-data/vendorPortal/john.json", VendorPortalTestData.class);
       // InputStream stream = ResourceLoader.getResource("");
      //  String content = IOUtils.toString(stream, StandardCharsets.UTF_8);
        System.out.println(testData.AnnualEarning());
    }
}
