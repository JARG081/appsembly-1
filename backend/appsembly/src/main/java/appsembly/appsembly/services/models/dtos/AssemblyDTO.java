package appsembly.appsembly.services.models.dtos;

import java.util.Date;

import lombok.Data;

@Data
public class AssemblyDTO {
    private Date startDate;

    private String title;

    private String proposal;
}
