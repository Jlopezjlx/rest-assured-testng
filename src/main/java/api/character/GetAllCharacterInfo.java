package api.character;

import api.BaseAPI;

import static io.restassured.RestAssured.given;

public class GetAllCharacterInfo extends BaseAPI {

    String apiPath="/characters/";

    public GetAllCharacterInfo(String baseURI) {
        super(baseURI);
    }

    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecification=requestSpecBuilder.build();
    }

    @Override
    protected void executeRequest() {
        apiResponse = given().spec(requestSpecification).get();
    }

    @Override
    protected void validateResponse() {
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecification=responseSpecBuilder.build();
        apiResponse.then().spec(responseSpecification);
    }
}
