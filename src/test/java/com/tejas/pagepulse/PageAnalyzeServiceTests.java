package com.tejas.pagepulse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tejas.pagepulse.Service.PageAnalyzerService;
import com.tejas.pagepulse.model.AnalyzeResponse;

@SpringBootTest
public class PageAnalyzeServiceTests {
    @Autowired
    PageAnalyzerService pageAnalyzerService;

    @Test
    void testValidUrl()
    {
        AnalyzeResponse response =  pageAnalyzerService.analyzePage("https://www.google.com");
        assertEquals(200, response.getHttpStatus());
        assertNotNull(response.getTitle());
        assertTrue(response.getWordCount() > 0);
    }

    @Test
    void testInvalidUrl()
    {
        AnalyzeResponse response =  pageAnalyzerService.analyzePage("abcd");
        assertEquals("Invalid URL!", response.getError());
    }

    @Test
    void testNonHtmlUrl()
    {
        AnalyzeResponse response =  pageAnalyzerService.analyzePage("https://sample-files.com/downloads/documents/txt/simple.txt");
        assertEquals("The URL does not point to a HTML webpage!", response.getError());
    }
}
