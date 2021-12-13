package mani.example.movieinfoservice.resources;

import mani.example.movieinfoservice.helper.fileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class fileUploadController {

    @Autowired
    private fileUploadHelper fileuploadhelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){

        System.out.println("hiiiiiiiiiiiiiiii");
        try{
            if(file.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file is empty");
            }
            boolean f = fileuploadhelper.uploadFiles(file);

            if(f)
            {
                return  ResponseEntity.ok("file uploaded");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("upload correctly");

    }


}
