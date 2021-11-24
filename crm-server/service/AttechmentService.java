package uz.pdp.srmserver.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.srmserver.entitiy.Attechment;
import uz.pdp.srmserver.entitiy.AttechmentContent;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.repository.AttechmentContentRepository;
import uz.pdp.srmserver.repository.AttechmentRepository;

import java.io.*;
import java.util.Iterator;
import java.util.UUID;

@Service
public class AttechmentService {
//    @Autowired
//    AttechmentRepository attachmentRepository;
//
//    @Autowired
//    AttechmentContentRepository attachmentContentRepository;
//
//
//    public ApiResponse saveToDB(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
//
//        try {
//            Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
//            while (fileNames.hasNext()) {
//                MultipartFile file = multipartHttpServletRequest.getFile(fileNames.next());
//                Attechment attachment = new Attechment();
//                assert file != null;
//                attachment.setOriginalName(file.getOriginalFilename());
//                attachment.setFileName(file.getName());
//                attachment.setContentType(file.getContentType());
//                attachment.setSize(file.getSize());
//                Attechment savedAttachment = attachmentRepository.save(attachment);
//                AttechmentContent attachmentContent = new AttechmentContent();
//                attachmentContent.setAttechment(savedAttachment);
//                attachmentContent.setBytes(file.getBytes());
//                attachmentContentRepository.save(attachmentContent);
//
//                return new ApiResponse("Saved", true);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ApiResponse("Error",false);
//    }
//
//
//
//
//    public ResponseEntity<byte[]> getById(UUID id) throws IOException {
//        Attechment attachment = attachmentRepository.getById(id);
//        if (attachment.getPath() != null) {
//            File file = ResourceUtils.getFile("classpath:static/appFiles/" + attachment.getOriginalName());
//            InputStream in = new FileInputStream(file);
//            byte[] bdata = FileCopyUtils.copyToByteArray(in);
//            return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType()))
//                    .header(HttpHeaders.CONTENT_DISPOSITION,
//                            "attachment; fileName=\"" + attachment.getOriginalName() + "\"")
//                    .body(bdata);
//        } else {
//            AttechmentContent content = attachmentContentRepository.findByAttachment(attachment);
//            return ResponseEntity.ok().contentType(MediaType.valueOf(attachment.getContentType()))
//                    .header(HttpHeaders.CONTENT_DISPOSITION,
//                            "attachment; fileName=\"" + attachment.getOriginalName() + "\"")
//                    .body(content.getBytes());
//        }
//    }
//
//    public String saveToFileSystem(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
//        Iterator<String> fileNames = multipartHttpServletRequest.getFileNames();
//        while (fileNames.hasNext()) {
//            MultipartFile file = multipartHttpServletRequest.getFile(fileNames.next());
//            assert file != null;
//
//            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/static/appFiles/" + file.getOriginalFilename());
//            byte[] bytes = file.getBytes();
//            fileOutputStream.write(bytes);
//            fileOutputStream.close();
//
//            Attechment attachment = new Attechment(
//                    file.getOriginalFilename(),
//                    file.getContentType() != null ? file.getContentType() : "unknown",
//                    (int) file.getSize(),
//                    "src/main/resources/static/appFiles/" + file.getOriginalFilename()
//            );
//            Attechment savedAttachment = attachmentRepository.save(attachment);
//        }
//        return "Saqlandi";
//    }
}
