package artefact.logAndReport.src.test.java.com.mycompany.testing;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;


@RunWith(CucumberJvmTest.class)
@CucumberOptions(features = {"src/test/resources/"})
public class CucumberJvmTest {

}
