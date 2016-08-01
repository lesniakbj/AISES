package com.aises.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

/**
 * Created by Brendan on 7/25/2016.
 *
 * A post that can be put on a wall. In progress.
 */
public class Post {
    private int id;
    private int userId;
    private String text;
    private int length;
    private LocalDateTime dateCreated;

    @SuppressWarnings("unused")
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

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
