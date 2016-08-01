package com.aises.service;

import com.aises.controller.UploadController;
import com.aises.domain.File;
import com.aises.service.interfaces.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by Brendan on 7/30/2016.
 *
 * A service to handle Uploads for the UploadController.
 */
public class UploadService implements Service {
    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    private static UploadService instance;

    private UploadService() {
        logger.debug("Creating a service for Uploads");
    }

    public static UploadService getInstance() {
        if(instance == null) {
            instance = new UploadService();
        }
        return instance;
    }

    public File uploadFile(Part fileUpload) {
        logger.debug("File attempting to upload: {}", fileUpload.getSubmittedFileName());
        String[] parts = fileUpload.getSubmittedFileName().split("\\.");

        if(parts.length <= 1) {
            logger.error("File uploaded without extension! Length: {}", parts.length);
            return null;
        }

        boolean validFile = isValidFileExtension(parts[parts.length - 1]);
        if(validFile) {
            File file = createFileFromParts(fileUpload, parts);
            logger.debug("File created: {}/{}", file.getFileName(), file.getExtension());
            return file;
        }

        logger.debug("Invalid file uploaded!");
        return null;
    }

    private File createFileFromParts(Part fileUpload, String[] fileParts) {
        String fileName = String.join(".", (CharSequence[]) Arrays.copyOfRange(fileParts, 0, fileParts.length - 1));
        String extension = "." + fileParts[fileParts.length - 1];

        // Create the initial file, then save the Part to disk,
        // retrieving the filepath to the resource.
        File file = new File();
        file.setFileName(fileName);
        file.setExtension(extension);
        file.setDateUploaded(LocalDateTime.now());
        file.setFilePath(writeUploadToDisk(fileUpload, file.getFileName() + file.getExtension()));

        return file;
    }
    private String writeUploadToDisk(Part fileUpload, String fileName) {
        Path out = Paths.get(UploadController.UPLOAD_DIRECTORY + "\\" + fileName);

        try(final InputStream in = fileUpload.getInputStream()) {
            Files.copy(in, out, StandardCopyOption.REPLACE_EXISTING);
            fileUpload.delete();
        } catch (IOException e) {
            logger.error("Unable to write file to disk!", e);
        }

        return out.toString();
    }

    private boolean isValidFileExtension(String ext) {
        return isValidImageExtension(ext) || isValidVideoExtension(ext);
    }
    private boolean isValidImageExtension(String ext) {
        return ext.length() > 0;
    }
    private boolean isValidVideoExtension(String ext) {
        return ext.length() < 0;
    }
}
