package api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.io.IOException;
import java.util.Arrays;

import pojo.characters;

import javax.swing.plaf.synth.SynthEditorPaneUI;


public abstract class BaseAPI {

    protected String baseURI;
    protected RequestSpecBuilder requestSpecBuilder;
    protected RequestSpecification requestSpecification;
    protected ResponseSpecBuilder responseSpecBuilder;
    protected ResponseSpecification responseSpecification;
    protected String character;
    protected String data;
    protected Response apiResponse;
    protected int expectedStatusCode;

    public BaseAPI(String baseURI){
        this.baseURI=baseURI;
        requestSpecBuilder=new RequestSpecBuilder();
        responseSpecBuilder=new ResponseSpecBuilder();
    }

    protected Response getApiResponse() {
        return apiResponse;
    }

    public String getApiResponseAsString() {
        return apiResponse.asString();
    }

    public <T> T getSingleCharacterAsPOJO(Class<T> type) throws IOException {
        character = getApiResponseAsString();
        String data = new String(character.substring( 1, character.length() - 1 ));
        return new ObjectMapper().readValue(data, type);
    }

    public void getAllCharacterAsPOJOAndPrint() throws IOException {
        character = getApiResponseAsString();
        characters[] characters = apiResponse.body().as(pojo.characters[].class);
        Arrays.stream(characters).forEach(value -> {
            System.out.print("Character Name: " + value.getName());
            System.out.print("Portrayed: " + value.getPortrayed());
            System.out.print("---------------------------");
        });
    }

    public int getExpectedStatusCode() {
        return expectedStatusCode;
    }

    public void setExpectedStatusCode(int expectedStatusCode) {
        this.expectedStatusCode = expectedStatusCode;
    }

    protected abstract void createRequest();
    protected abstract void executeRequest();
    protected abstract void validateResponse();

    public void perform(){
        createRequest();
        executeRequest();
        validateResponse();
    }
}
