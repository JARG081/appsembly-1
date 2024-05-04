package appsembly.appsembly.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    // private String personalCode;

    // private Boolean status; ESTO ES PARA SABER SI EL USUARIO ESTÁ ACTIVO O NO

    @Enumerated(EnumType.STRING)
    private RolesEntity role;

    @OneToMany(mappedBy = "user")
    private List<AttendanceEntity> attendance;

    @OneToMany(mappedBy = "user")
    private List<VoteEntity> vote;
}
