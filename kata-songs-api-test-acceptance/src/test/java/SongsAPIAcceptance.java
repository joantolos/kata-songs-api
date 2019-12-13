import com.joantolos.kata.songs.api.SongsAPI;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration
@TestPropertySource
@SpringBootTest(classes = SongsAPI.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SongsAPIAcceptance implements En {

    private Response response;
    private RequestSpecification request;

    public SongsAPIAcceptance() {

        Before(() -> {
            this.request = RestAssured.given();
        });

        Given("^I perform a \"([^\"]*)\" to \"([^\"]*)\" end point$", (String method, String url) -> {
            response = request.request(method, url);
        });

        Then("^the response at the json path \"([^\"]*)\" includes \"([^\"]*)\"$", (String jsonPath, String expectedValue) -> {
            response.then().body(jsonPath, Matchers.is(expectedValue));
        });
        Then("^the response at the json path \"([^\"]*)\" includes \"([^\"]*)\" or \"([^\"]*)\"$", (String jsonPath, String expectedFirstValue, String expectedSecondValue) -> {
            response.then().body(jsonPath, Matchers.anyOf(Matchers.is(expectedFirstValue), Matchers.is(expectedSecondValue)));
        });
    }

}
