package uz.gullbozor.gullbozor.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_entity")
public class CompanyEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;// ismi

    @Column(name = "password",nullable = false)
    private String password;// userning passwordi

    private String usernameTest;

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



    private Long companyOwnerId;
    private String companyName;

    private String location;
    private String tgLink;
    private String phoneNumber;
    private Integer regionId;
    private Integer cityId;
    private Integer dillerId;
    private Double partOfWindow;
    private Double partOfDoor;
    private Double value;
    private Long tgClick;
    private Long mapClick;
    private Long phoneClick;
    private Long view;
    private Boolean installIsFree;


}
