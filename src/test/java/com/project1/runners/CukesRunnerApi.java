package com.project1.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Created in Project1 by Gokhan on Apr, 2020
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "json:target/apiTests.json",
                "html:target/api_html_report.html",
                "rerun:/target/apiReruns"
        },
        features = "src/test/resources/api_features",
        glue = "com/project1/step_api_definitions",
        dryRun = false,
        tags = "@HarryPotter"

)
public class CukesRunnerApi {
}
