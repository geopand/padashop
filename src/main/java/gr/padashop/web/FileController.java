package gr.padashop.web;


import gr.padashop.helpers.ResponseHelper;
import gr.padashop.services.ImageFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final ImageFileService imageFileService;

    public FileController(ImageFileService imageFileService) {
        this.imageFileService = imageFileService;
    }

    @PostMapping("/upload-file")
    public boolean uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            imageFileService.save(file);
            return true;
        } catch (IOException e) {
            logger.error("Exception occurred during the save of file {} with exception {}", file.getName(), e.getMessage(), e);
        }
        return false;
    }


    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("file") String fileName) {
        try {
            File file = imageFileService.getFileByFileName(fileName);

            return ResponseEntity.ok()
                    .headers(ResponseHelper.getDownloadHeaders(fileName))
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(Files.newInputStream(file.toPath())));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Enables download with resume capability and slitted in multiple parts */
    @GetMapping("/download-fast")
    public ResponseEntity<Resource> downloadFast(@RequestParam("file") String fileName) {
        try {
            File file = imageFileService.getFileByFileName(fileName);

            return ResponseEntity.ok()
                    .headers(ResponseHelper.getDownloadHeaders(fileName))
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new FileSystemResource(file));
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }



}
