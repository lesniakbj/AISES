package com.aises.domain;

import java.time.LocalDateTime;

/**
 * Created by Brendan on 7/30/2016.
 */
public class File {
    private String path;
    private String fileName;
    private String extension;
    private LocalDateTime dateUploaded;

    public String getFilePath() {
        return path;
    }
    public String getFileName() {
        return fileName;
    }
    public String getExtension() {
        return extension;
    }
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
}
