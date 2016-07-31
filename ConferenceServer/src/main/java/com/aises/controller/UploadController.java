package com.aises.controller;

import com.aises.domain.File;
import com.aises.server.Routes;
import com.aises.service.UploadService;
import com.aises.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Spark;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * Created by Brendan on 7/30/2016.
 *
 * Controller for uploading files (images/videos) to the
 * server.
 */
public class UploadController {
    private static UploadService uploadService;
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    public static final String UPLOAD_DIRECTORY = "uploads";
    private static final String UPLOAD_FORM_NAME = "upload-file";
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 50;
    private static final int WRITE_THRESHOLD = 1024;


    static {
        uploadService = UploadService.getInstance();
    }

    public UploadController() {
        logger.debug("Creating a controller for Uploads");

        Spark.post(Routes.UPLOAD, (req, resp) -> uploadMultiPartFile(req, resp), JSONUtils.JSON());
        Spark.after(Routes.UPLOAD, (req, resp) -> resp.header("Content-Type", "application/json"));
    }

    private File uploadMultiPartFile(Request req, Response resp) throws IOException, ServletException {
        logger.debug("Uploading new file");

        MultipartConfigElement mce = new MultipartConfigElement(UPLOAD_DIRECTORY, MAX_FILE_SIZE, MAX_FILE_SIZE, WRITE_THRESHOLD);
        req.raw().setAttribute("org.eclipse.jetty.multipartConfig", mce);

        Part fileUpload = req.raw().getPart(UPLOAD_FORM_NAME);
        if(fileUpload == null) {
            return null;
        }

        return uploadService.uploadFile(fileUpload);
    }
}
