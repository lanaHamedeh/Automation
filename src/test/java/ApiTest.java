import base.Base;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import loginAPI.LoginPage;
import memebrAPI.MemberPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import signUpAPI.SignUpPage;

public class ApiTest extends Base {

    String userName = "lana" + Math.floor(Math.random() * 10000) + "@yahoo.com";
    String password = "QWEqwe!1";
    String email;
    String firstName;
    String tokenValue;
    SignUpPage signUpPage = new SignUpPage(); Response signUpResponse;
    LoginPage loginPage = new LoginPage(); Response loginResponse;
    MemberPage memberPage = new MemberPage(); Response memberDetails;
    @Test(description = "Verify creating new account", priority = 1)
    public void checkSignUp() {

       signUpResponse = signUpPage.signUpPost(userName,password);
        Assert.assertEquals(signUpResponse.getStatusCode(), 200);
        JsonPath jsonPathEvaluators = signUpResponse.jsonPath();
        email = jsonPathEvaluators.get("email").toString();
        firstName = jsonPathEvaluators.get("user_metadata.firstName").toString();

    }

    @Test(description = "check status Code and get access Token", priority = 2)
    public void checkingDLogin() {
        loginResponse =loginPage.loginPost(email,password);
        loginResponse.prettyPrint();
        Assert.assertEquals(loginResponse.getStatusCode(), 200);
        JsonPath jsonPathEvaluators = loginResponse.jsonPath();
        tokenValue = jsonPathEvaluators.get("token_type").toString() + "  "+jsonPathEvaluators.get("access_token").toString();
        System.out.println(tokenValue);
    }
    @Test(description = "check status Code and get access Token" , priority = 3)
    public void checkingGetMemberDetails() {
        memberDetails = memberPage.getMemberDetails(tokenValue);
        memberDetails.prettyPrint();
        Assert.assertEquals(memberDetails.getStatusCode(), 200);
        JsonPath jsonPathEvaluators = memberDetails.jsonPath();
        Assert.assertEquals(firstName,jsonPathEvaluators.get("first_name").toString());
        Assert.assertEquals(email,jsonPathEvaluators.get("email_address").toString());
        Assert.assertEquals("BASE",jsonPathEvaluators.get("tier").toString());
        Assert.assertEquals("false",jsonPathEvaluators.get("extra_data.sw_onboarding").toString());
    }

}