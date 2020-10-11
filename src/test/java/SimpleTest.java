import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleTest {
    public RemoteWebDriver driver;
    private static final String SELENIUM_URL="http://127.0.0.1:4444/wd/hub";

    @BeforeTest
    public void start() throws MalformedURLException {
        this.driver = new RemoteWebDriver(new URL(SELENIUM_URL), new ChromeOptions());
    }

    @Test
    public void SimpleTest() throws IOException {
        this.driver.get("http://google.com/");
        this.takeScreenshot();
    }

    private void takeScreenshot() throws IOException {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        File sourse = ts.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourse, new File("./screenshot.png"));
        System.out.println("Screenshot is taken...");
    }

    @AfterTest
    public void closeSeleniumSession(){
        this.driver.close();
        this.driver.quit();
    }

}
