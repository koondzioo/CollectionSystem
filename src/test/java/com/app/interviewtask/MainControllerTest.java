package com.app.interviewtask;


import com.app.interviewtask.model.CollectionImages;
import com.app.interviewtask.model.Image;
import com.app.interviewtask.repository.CollectionImagesRepository;
import com.app.interviewtask.repository.ImageRepository;
import com.app.interviewtask.service.ImageService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {


    private final String CONTEXT_PATH = "/app";
    private MockMvc mockMvc;

    private ImageService imageService;

    @Mock
    private ImageRepository imageRepository;
    @Mock
    private CollectionImagesRepository collectionImagesRepository;


    @Before
    public void beforeEachTest() {
/*        imageService = new ImageService(imageRepository, collectionImagesRepository);
        Image imageFirst = Image.builder().filename("C:\\xxx\\aaa.jpg").url("wp.pl").collectionImages(new CollectionImages()).build();
        Image imageSecond = Image.builder().filename("C:\\xxx\\bbb.jpg").url("onet.pl").collectionImages(new CollectionImages()).build();
        Mockito.when(imageService.findById(1L)).thenReturn(imageFirst);
        Mockito.when(imageService.findById(2L)).thenReturn(imageSecond);*/

        RestAssured.baseURI="http://localhost";
        RestAssured.port=8080;
    }


    @Test
    public void statusCodeCheck() throws Exception {
        Response response = given()
                .contentType("text/plain")
                /*.accept("text/plain")*/
                .body("https://wp.pl/")
                .when()
                .post(CONTEXT_PATH + "/images")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .extract()
                .response();
    }

    @Test
    public void checkImagesListSize() throws Exception
    {
        Response response = given()
                .contentType("text/plain")
                /*.accept("text/plain")*/
                .body("https://google.pl/")
                .when()
                .post(CONTEXT_PATH + "/images")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .extract()
                .response();
      Assert.assertEquals(3, Arrays.asList(response.getBody().as(Image[].class)).size());
    }

    @Test
    public void invalidDownloadImage() throws Exception{
        given().when().get(CONTEXT_PATH + "/images/100000").then().statusCode(500);
    }


    @Test
    public void invalidDownloadText() throws Exception{
        given().when().get(CONTEXT_PATH + "/text/100000").then().statusCode(500);
    }

    @Test
    public void checkConnection(){
        given().when().get("https://google.pl").then().statusCode(200).assertThat().body("html.title", equalTo("Google"));
    }

    @Test
    public void checkImage(){
        Image image = Image.builder().id(1L).url("wp.pl").filename("C:\\xxx\\aaa.jpg").build();
        ImageService imageService = mock(ImageService.class, Mockito.RETURNS_DEEP_STUBS);
        Mockito.when(imageService.findById(1L)).thenReturn(image);

        get(CONTEXT_PATH+"/images/"+image.getId()).then().assertThat().body("id", equalTo(image.getId()));
    }
}
