package com.aises.domain;

import com.aises.domain.interfaces.Attachment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 */
public class Post {
    private int id;
    private int userId;
    private String text;
    private int length;
    private LocalDateTime dateCreated;
    private List<Attachment> attachments;

    private static final int MAX_ATTACHMENTS = 3;

    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public String getText() {
        return text;
    }
    public int getLength() {
        return length;
    }
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment newAttachment) {
        if(attachments.size() < MAX_ATTACHMENTS) {
            attachments.add(newAttachment);
        }
    }
}
