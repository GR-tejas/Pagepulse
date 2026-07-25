package com.tejas.pagepulse.model;

public class AnalyzeResponse {
    private int httpStatus;
    private long responseTime;
    private String title;
    private String metaDescription;
    private int h1Count;
    private int missingAltImages;
    private int wordCount;
    private String error;

    // Getters and Setters

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(long responseTime) {
        this.responseTime = responseTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public int getH1Count() {
        return h1Count;
    }

    public void setH1Count(int h1Count) {
        this.h1Count = h1Count;
    }

    public int getMissingAltImages() {
        return missingAltImages;
    }

    public void setMissingAltImages(int missingAltImages) {
        this.missingAltImages = missingAltImages;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
