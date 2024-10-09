package net.akarmanov.projectplace.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import net.akarmanov.projectplace.models.UserRole;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Size(max = 255)
    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 255)
    @Column(name = "middle_name")
    private String middleName;

    @Size(max = 32)
    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @Size(max = 32)
    @Column(name = "telegram_id", length = 32)
    private String telegramId;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @NotNull
    @Column(name = "role", nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private UserRole role;

}