package uz.gullbozor.gullbozor.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_owner")
public class CompanyOwner extends BaseEntity implements UserDetails {

    @Column(name = "username", length = 50, nullable = false)
    private String username;// ismi

    @Column(name = "password",nullable = false)
    private String password;// userning passwordi

    private String usernameTest;

    private String surname;

    private Long shopId;

    @UpdateTimestamp
    private Timestamp updateAt; // qachon tahrirlanganligi

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    private boolean accountNonExpired = true; // bu userning amal qilish muddati o'tmaganligi

    private boolean accountNonLocked = true; // bu userning blocklanmaganligi

    private boolean credentialsNonExpired = true; // bu userning ishonchlilik muddati o'tganligi

    private boolean enabled = false; // accauntni Yoniq toki o'chiqligi

//    private String emailCode;


    //------------// BU USERDETAILSNI METHODLARI //-----------------

    //Bu userning huquqlari
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    //Bu userning usernameni qaytaruvchi method
    @Override
    public String getUsername() {
        return username;
    }

    //Bu accauntni amalqilsih muddatini qaytaradi
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    //accaunt blocklanganligi holatini qaytaradi
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }


    //accauntni ishonchliligi tugaganligini qaytaradi
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    //accauntni Yoniq toki o'chiqligini qaytaradi
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
    // base entityda qachon user ro'yxatdan o'tganligi bor


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CompanyOwner that = (CompanyOwner) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
