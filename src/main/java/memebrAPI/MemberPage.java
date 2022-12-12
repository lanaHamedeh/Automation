package memebrAPI;


import base.Base;
import io.restassured.response.Response;

public class MemberPage extends Base {
    String url = "https://maf-holding-dev.apigee.net/v1/gravity/dk-gravity-memberdetails";
    public Response getMemberDetails(String token) {
        setURL(url);
   return httpRequest().when().headers(getX_API_KEY(),getXApiKeyValue(),"Authorization",token).get();

    }
}
