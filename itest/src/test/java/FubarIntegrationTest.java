import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;

import static org.junit.Assert.assertNotNull;

public class FubarIntegrationTest {
    private FirefoxDriver driver;

    @Before
    public void before() {
        driver = getFirefoxDriver();
    }

    @After
    public void saveScreenshotAndCloseBrowser() throws IOException {
        driver.quit();
    }

    public static FirefoxDriver getFirefoxDriver() {
        ProfilesIni profiles = new ProfilesIni();
        String profileName = "default";
        FirefoxProfile profile = profiles.getProfile(profileName);
        assertNotNull(String.format("Unable to load firefox profile: %s", profileName), profile);

        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setAcceptUntrustedCertificates(true);
        return new FirefoxDriver(profile);
    }

    @Test
    public void testAccept() throws Exception {
        driver.get("https://www.google.com");
        WebElement element = driver.findElementById("searchform");
        assertNotNull(element);
    }
}
