package com.aises.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.LocalDateTime;

/**
 * Created by Brendan on 7/30/2016.
 *
 * A File that can be Uploaded. Mostly images.
 */
public class File {
    private String path;
    private String fileName;
    private String extension;
    private LocalDateTime dateUploaded;

    @SuppressWarnings("unused")
    public String getFilePath() {
        return path;
    }
    public String getFileName() {
        return fileName;
    }
    public String getExtension() {
        return extension;
    }
    @SuppressWarnings("unused")
    public LocalDateTime getDateUploaded() {
        return dateUploaded;
    }

    public void setFilePath(String path) {
        this.path = path;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }
    public void setDateUploaded(LocalDateTime dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
