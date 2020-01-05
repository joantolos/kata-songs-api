import com.joantolos.kata.songs.api.remote.mocks.CountryAPIMock;
import com.joantolos.kata.songs.api.remote.mocks.LyricsAPIMock;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.FileNotFoundException;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:../../build/reports/component/html-report/",
                "json:../../build/reports/component/report.json"
        },
        features = "src/test/resources/features"
)
public class SongsAPIAcceptanceTestRunner {

        private static LyricsAPIMock lyricsAPIMock;
        private static CountryAPIMock countryAPIMock;

        @BeforeClass
        public static void setup() throws FileNotFoundException {
                try {
                        lyricsAPIMock = new LyricsAPIMock(7201);
                        countryAPIMock = new CountryAPIMock(7202);
                } catch (Exception e) {
                        throw new FileNotFoundException(e.getMessage());
                }
        }

        @AfterClass
        public static void teardown() {
                lyricsAPIMock.stop();
                countryAPIMock.stop();
        }
}
