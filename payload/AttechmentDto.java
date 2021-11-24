package uz.pdp.srmserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttechmentDto {

    private UUID id;

    private String originalName;

    private long size;

    private String contentType;

    private String fileName;

    private String path;

}
