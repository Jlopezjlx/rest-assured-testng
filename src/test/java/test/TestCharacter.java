package test;

import api.character.GetCharacterInfoById;
import api.character.GetAllCharacterInfo;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.characters;
import utils.CSVAnnotation;
import utils.GenericDataProvider;
import utils.PropertiesManager;
import io.restassured.response.Response;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.io.IOException;
import java.util.Map;


public class TestCharacter extends BaseTest {

    protected Response apiResponse;

    @Test(dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
    @CSVAnnotation.CSVFileParameters(path = "src\\test\\java\\test-data\\CharacterInfo.csv", delimiter = "###")
    public void GetCharacterByIdTest(int rowNo, Map<String, String> inputData) throws IOException {


        GetCharacterInfoById getCharacterInfoById = new GetCharacterInfoById(PropertiesManager.getProperty("baseURI"));
        getCharacterInfoById.setId(PropertiesManager.getProperty("characterId"));
        getCharacterInfoById.setExpectedStatusCode(200);
        getCharacterInfoById.perform();

        characters character = getCharacterInfoById.getSingleCharacterAsPOJO(characters.class);
        System.out.print("Character name is: " + character.getName());
        System.out.print("Character birthday is: " + character.getBirthday());
        Integer actualId=character.getCharId();
        String expectedId=inputData.get("id");
        Assert.assertEquals( expectedId,String.valueOf(actualId));
    }

    @Test(dataProvider = "dataproviderForTestCase", dataProviderClass = GenericDataProvider.class)
    @CSVAnnotation.CSVFileParameters(path = "src\\test\\java\\test-data\\CharacterInfo.csv", delimiter = "###")
    public void GetAllCharacterInfoTest(int rowNo, Map<String, String> inputData) throws IOException {


        GetAllCharacterInfo getAllCharacterInfo = new GetAllCharacterInfo(PropertiesManager.getProperty("baseURI"));
        getAllCharacterInfo.setExpectedStatusCode(200);
        getAllCharacterInfo.perform();
        getAllCharacterInfo.getAllCharacterAsPOJOAndPrint();
    }
}
