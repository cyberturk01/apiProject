package com.project1.step_definitions;

import com.project1.utilities.Driver;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {

    @Before()
    public void setUp(){
        Driver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Driver.get().manage().window().fullscreen();
    }

    @After()
    public void tearDown(Scenario scenario){
        //if the scenario fails take the screenshot
        if(scenario.isFailed()){
            final byte[]screenshot=((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
//            scenario.embed(screenshot,"image/png");
            scenario.embed(screenshot,"image/png", scenario.getName());
        }
        Driver.closeDriver();
    }
}
