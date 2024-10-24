package net.akarmanov.projectplace.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.akarmanov.projectplace.models.UserRole;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @NotNull
    @Size(max = 255)
    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Size(max = 255)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 255)
    @Column(name = "last_name")
    private String lastName;

    @Size(max = 255)
    @Column(name = "middle_name")
    private String middleName;

    @Size(max = 32)
    @Column(name = "phone_number", length = 32, unique = true)
    private String phoneNumber;

    @Size(max = 32)
    @Column(name = "telegram_id", length = 32, nullable = false, unique = true)
    private String telegramId;

    @NotNull
    @Column(name = "role", nullable = false, length = 32)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TeamCard> userTeamCards;

    @ManyToMany(mappedBy = "streamsUserUsers")
    private Set<Stream> streamsUserStreams;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserPhoto photo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return telegramId;
    }
}