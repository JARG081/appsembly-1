package appsembly.appsembly.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class Message {
    private String type;
    private String content;
    private UUID assemblyID;
    private UUID questionID;
    private String[] options;
    private int time;
}
