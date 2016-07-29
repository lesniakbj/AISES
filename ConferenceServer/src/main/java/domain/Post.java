package domain;

import domain.interfaces.Attachment;

import java.util.List;

/**
 * Created by Brendan on 7/25/2016.
 */
public class Post {
    private int id;
    private String text;
    private int textLength;
    private List<Attachment> attachments;

    private static final int MAX_ATTACHMENTS = 3;

    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public int getTextLength() {
        return textLength;
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
    public void setTextLength(int textLength) {
        this.textLength = textLength;
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
