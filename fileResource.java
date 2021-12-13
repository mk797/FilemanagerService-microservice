package mani.example.movieinfoservice.resources;

import org.springframework.core.io.Resource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;

@RestController
@RequestMapping("/file")
public class fileResource {

    @RequestMapping("/{filename}")
    public String readFile(@PathVariable("filename") String filename) throws IOException, URISyntaxException {


        String path= "mani/" + filename + ".pdf";
       File file= new ClassPathResource(path).getFile();
        String content = new String(
                Files.readAllBytes(file.toPath()));

        return content;

    }

    private static File resolveFileFromResources(String filename) throws URISyntaxException {
        System.out.println(filename);

        return Paths.get(
                Thread.currentThread().getContextClassLoader().getResource(filename).toURI()).toFile();
    }

    private File getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("fileResource.class");
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

}

