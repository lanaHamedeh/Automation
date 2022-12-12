package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;

@Getter
public class Base {
    String X_API_KEY ="x-api-key";
    String XApiKeyValue ="GfqP7b2I99sUMkbxGEk5Xk56RscaWRuo";
    public String setURL(String url) {
        return RestAssured.baseURI = url;
    }

    public RequestSpecification httpRequest() {
       return RestAssured.given();
    }
}
