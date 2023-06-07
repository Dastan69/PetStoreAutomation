package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDTests {

    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostUser(String userID,String username,String fname,String lname,String email,String pwd,String pn){

        User userPayload = new User();

        userPayload.setId(Integer.parseInt(userID));
        userPayload.setUsername(username);
        userPayload.setFirstname(fname);
        userPayload.setLastname(lname);
        userPayload.setEmail(email);
        userPayload.setPassword(pwd);
        userPayload.setPhone(pn);

        Response response= UserEndPoints.createUser(userPayload);

        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testGetUser(String username){

        Response response = UserEndPoints.readUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test(priority = 3,dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUserByUsername(String username){

        Response response = UserEndPoints.deleteUser(username);

        Assert.assertEquals(response.getStatusCode(),200);

    }
}
