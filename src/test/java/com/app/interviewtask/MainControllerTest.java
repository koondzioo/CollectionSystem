package com.app.interviewtask;


import com.app.interviewtask.model.Image;
import com.app.interviewtask.repository.CollectionImagesRepository;
import com.app.interviewtask.repository.ImageRepository;
import com.app.interviewtask.service.ImageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {

    private MockMvc mockMvc;

    private ImageService imageService;

    @Mock
    private ImageRepository imageRepository;
    @Mock
    private CollectionImagesRepository collectionImagesRepository;


    @Before
    public void beforeEachTest() {
        imageService = new ImageService(imageRepository, collectionImagesRepository);
        Image imageFirst = Image.builder().filename("C:\\xxx\\aaa.jpg").url("wp.pl").build();
        Image imageSecond = Image.builder().filename("C:\\xxx\\bbb.jpg").url("onet.pl").build();
        Mockito.when(imageService.findById(1L)).thenReturn(imageFirst);
        Mockito.when(imageService.findById(2L)).thenReturn(imageSecond);
    }


    @Test
    public void sizeImages() throws Exception {
        Assert.assertEquals(2, imageService.findAll());
    }

    @Test
    public void test()
    {

        given()
                .post()
    }
}
