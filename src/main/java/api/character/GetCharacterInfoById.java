package api.character;

import api.BaseAPI;

import static io.restassured.RestAssured.given;

public class GetCharacterInfoById extends BaseAPI {

    String apiPath="/characters/{id}";
    String id;

    public GetCharacterInfoById(String baseURI) {
        super(baseURI);
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected void createRequest() {
        requestSpecBuilder.setBaseUri(baseURI);
        requestSpecBuilder.setBasePath(apiPath);
        requestSpecBuilder.addPathParam("id",id);
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
