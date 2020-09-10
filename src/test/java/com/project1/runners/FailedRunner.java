package com.project1.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created in Project1 by Gokhan on Apr, 2020
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt",
        glue= "com.project1/step_definitions",
        plugin = {
                "json:target/rerun.json"
        }
)

public class FailedRunner {
}
