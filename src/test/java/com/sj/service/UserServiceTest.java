package com.sj.service;

import com.sj.model.Content;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testContentFromMongo() throws Exception {
        Content savedContent = userService.saveContent(Files.readAllBytes(Paths.get(this.getClass().getResource("/application.properties").toURI())), "", "");
        Assert.assertNotNull(savedContent);
        Content resultContent = userService.getContentFromMongo(savedContent.getId());
        Assert.assertNotNull(resultContent);
    }

    @Test
    public void testContentFromElasticSearch() throws Exception {
        Content savedContent = userService.saveContent(Files.readAllBytes(Paths.get(this.getClass().getResource("/application.properties").toURI())), "", "");
        Assert.assertNotNull(savedContent);
        List<Content> resultContent = userService.getContentFromElasticSearch(savedContent.getId(), "spring.jmx.enabled");
        Assert.assertNotNull(resultContent);
        Assert.assertTrue(resultContent.size() > 0);
    }
}
