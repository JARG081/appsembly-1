package appsembly.appsembly.entities;

import lombok.Data;

@Data
public class Message {
    private String type;
    private String content;
    private String assemblyID;
    private String[] options;
}
