package com.tejas.pagepulse.Service;

import java.io.IOException;
import java.net.SocketTimeoutException;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.tejas.pagepulse.model.AnalyzeResponse;

@Service
public class PageAnalyzerService {
    public AnalyzeResponse analyzePage(String url)
    {
        AnalyzeResponse analyzeResponse = new AnalyzeResponse();

        try
        {
            long startTime = System.currentTimeMillis();
            Response response = Jsoup.connect(url).timeout(10000).execute();
            long endTime = System.currentTimeMillis();

            String contentType = response.contentType();

            if(contentType == null || !contentType.startsWith("text/html"))
            {
                analyzeResponse.setError("The URL does not point to a HTML webpage!");
                return analyzeResponse;
            }

            analyzeResponse.setResponseTime(endTime - startTime);

            Document doc = response.parse();

            analyzeResponse.setHttpStatus(response.statusCode());
            
            analyzeResponse.setTitle(getTitle(doc));
            
            analyzeResponse.setMetaDescription(getMetaDesc(doc));
            
            analyzeResponse.setH1Count(getH1Count(doc));
            
            analyzeResponse.setMissingAltImages(getImgNoAltCount(doc));
            
            analyzeResponse.setWordCount(getWordCount(doc));

        } 
        catch (IllegalArgumentException e)
        {
            analyzeResponse.setError("Invalid URL!");
        }
        catch (SocketTimeoutException e)
        {
            analyzeResponse.setError("The website took too long to respond!");
        }
        catch (IOException e)
        {
            analyzeResponse.setError("Unable to connect to the website or analyze the webpage!");
        }
        return analyzeResponse;
    }

    private String getTitle(Document doc) {
        return doc.title();
    }

    private String getMetaDesc(Document doc) {
        return doc.select("meta[name=description]").attr("content");
    }

    private int getH1Count(Document doc) {
        return doc.select("h1").size();
    } 

    private int getImgNoAltCount(Document doc) {
        Elements imgList = doc.select("img");
        int count = 0;
        for (Element img : imgList)
            if(!img.hasAttr("alt") || img.attr("alt").isBlank())
                count ++;

        return count;
    }

    private int getWordCount(Document doc) {
        return doc.text().split("\\s+").length; 
    }
}
