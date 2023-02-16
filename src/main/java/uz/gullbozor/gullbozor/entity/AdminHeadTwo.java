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
@Table(name = "admin_entity")
public class AdminHeadTwo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long paid;
    private Long trial;
    private Long exits;

    private Short year;
    private Byte month;
    private String monthName;

}
