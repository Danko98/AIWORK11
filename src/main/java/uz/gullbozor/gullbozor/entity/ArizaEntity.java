package uz.gullbozor.gullbozor.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ariza_entity")
public class ArizaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String createAt;
    private Integer width;
    private Integer height;
    private Byte colorNumber;
    private Byte glassNumber;
    private Integer category;
    private Byte shelfSize;
    private String name;
    private String phoneNumber;
    private String regionId;

}
