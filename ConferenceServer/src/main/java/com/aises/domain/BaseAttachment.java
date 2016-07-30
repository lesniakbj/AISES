package com.aises.domain;

import com.aises.domain.interfaces.Attachment;

/**
 * Created by Brendan on 7/25/2016.
 */
public abstract class BaseAttachment implements Attachment {
    private AttachmentType type;
    private long size;

    public AttachmentType getType() {
        return type;
    }
    public long getSize() {
        return size;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }
    public void setSize(long size) {
        this.size = size;
    }
}
