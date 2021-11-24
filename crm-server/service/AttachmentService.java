package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.srmserver.entitiy.Attechment;
import uz.pdp.srmserver.entitiy.AttechmentContent;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.repository.AttachmentContentRepository;
import uz.pdp.srmserver.repository.AttachmentRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public ApiResponse upload(MultipartHttpServletRequest request) throws IOException {
        List<UUID> photoIds = new ArrayList<>();
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attechment attechment = new Attechment();
        attechment.setOriginalName(file.getOriginalFilename());
        attechment.setSize(file.getSize());
        attechment.setContentType(file.getContentType());
        attechment.setFileName(file.getName());
        attechment = attachmentRepository.save(attechment);
        AttechmentContent content = new AttechmentContent();
        content.setAttechment(attechment);
        content.setBytes(file.getBytes());
        attachmentContentRepository.save(content);
        photoIds.add(attechment.getId());

        return new ApiResponse(true, "Ok", photoIds);
    }

    public HttpEntity<?> download(UUID id) {
        Attechment byId = attachmentRepository.getById(id);
        AttechmentContent content = attachmentContentRepository.findByAttechment(byId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(byId.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filenem=\"" + byId.getFileName() + "\"")
                .body(content.getBytes());
    }
}
