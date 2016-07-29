package domain;

import domain.interfaces.Attachment;

/**
 * Created by Brendan on 7/25/2016.
 */
public class VideoAttachment extends BaseAttachment {
    public VideoAttachment() {
        setType(AttachmentType.VIDEO);
    }
}
