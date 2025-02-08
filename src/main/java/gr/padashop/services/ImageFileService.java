package gr.padashop.services;


import gr.padashop.configuration.AppConfig;
import gr.padashop.helpers.Helper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class ImageFileService {
    private final AppConfig config;

    public ImageFileService(AppConfig config) {
        this.config = config;
    }

    public void save(MultipartFile file) throws IOException {
        if (file == null) {
            throw new NullPointerException("file to save is null");
        }
        File targetFile = new File(config.getImageFilesPath() + File.separator
                + file.getOriginalFilename());
        if (!Objects.equals(targetFile.getParent(), config.getImageFilesPath())) {
            throw new SecurityException("Unsupported file name!");
        }

        Files.copy(file.getInputStream(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public File getFileByFileName(String fileName) throws FileNotFoundException {
        if (Helper.isEmpty(fileName)) {
            throw new NullPointerException("Filename is empty");
        }
        File fileToDownload = new File(config.getImageFilesPath() + File.separator + fileName);
        if (!Objects.equals(fileToDownload.getParent(), config.getImageFilesPath())) {
            throw new SecurityException("Unsupported file name!");
        }
        if (!fileToDownload.exists()) {
            throw new FileNotFoundException("No file exists with name: " + fileName);
        }

        return fileToDownload;
    }


}
