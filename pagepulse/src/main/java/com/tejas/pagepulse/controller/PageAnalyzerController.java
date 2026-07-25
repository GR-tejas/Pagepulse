package com.tejas.pagepulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tejas.pagepulse.Service.PageAnalyzerService;
import com.tejas.pagepulse.model.AnalyzeRequest;
import com.tejas.pagepulse.model.AnalyzeResponse;

@RestController
public class PageAnalyzerController {

    @Autowired
    public PageAnalyzerService pageAnalyzerService;

    @PostMapping("/analyze")
    public AnalyzeResponse analyzePage(@RequestBody AnalyzeRequest analyzeRequest)
    {
        return pageAnalyzerService.analyzePage(analyzeRequest.getUrl());
    }
}
