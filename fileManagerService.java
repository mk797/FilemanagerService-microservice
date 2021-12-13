package mani.example.moviecatalogservice.resources;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/filemanager")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class fileManagerService {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/getfile/{filename}")
    public String readfile(@PathVariable("filename") String filename) {
        String content = restTemplate.getForObject("http://file-manager-service/file/" + filename, String.class);
        return content;
    }


    @PostMapping("/uploadfile")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()));

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String Url = "http://localhost:8082/upload-file";
        ResponseEntity<String> response = restTemplate
                .postForEntity(Url, requestEntity, String.class);
            return response;

        }







}




