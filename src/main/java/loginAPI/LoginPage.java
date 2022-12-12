package loginAPI;


import base.Base;
import io.restassured.response.Response;

public class LoginPage extends Base {
    String url = "https://sit.maf-dev.auth0.com/oauth/token";

    public Response loginPost(String userName, String password) {
        setURL(url);
        return httpRequest().when().header("Content-Type", "application/json")
                .body(new LoginRequestBody().requestBody(userName, password))
                .post();

    }
}
