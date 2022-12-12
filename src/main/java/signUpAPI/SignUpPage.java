package signUpAPI;


import base.Base;
import io.restassured.response.Response;

public class SignUpPage extends Base {
    String url = "https://sit.maf-dev.auth0.com/dbconnections/signup";

    public Response signUpPost(String userName, String password) {
        setURL(url);
        return httpRequest().when().header("Content-Type", "application/json")
                .body(new SignUpRequestBody().requestBody(userName, password))
                .post();
    }
}
