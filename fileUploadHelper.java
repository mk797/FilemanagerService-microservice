package mani.example.movieinfoservice.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
public class fileUploadHelper {

    public final String UPLOAD_DIR = new ClassPathResource("mani").getFile().getAbsolutePath();

    public fileUploadHelper() throws IOException {
    }

    public boolean uploadFiles(MultipartFile file)
    {

        boolean f = false;

        try {
            Files.copy(file.getInputStream(), Path.of(UPLOAD_DIR + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            f=true;

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return f;


    }

}
