package domain;

import domain.interfaces.Attachment;

import java.util.Date;
import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 */
public class Post {
    private int id;
    private String text;
    private int length;
    private Date dateCreated;
    private List<Attachment> attachments;

    private static final int MAX_ATTACHMENTS = 3;

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public int getLength() {
        return length;
    }
    public Date getDateCreated() {
        return dateCreated;
    }
    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setDateCreated(Date dateCreated) {
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
