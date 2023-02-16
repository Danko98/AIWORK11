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
public class AdminEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long calls;
    private Long buys;
    private Long visitors;
    private Long paid;
    private Long trial;
    private Long exits;
    private Long clickTg;
    private Long clickMap;
    private Long page1;
    private Long page2;
    private Long page3;
    private Long page4;
    private Long page5;
    private Long page6;
    private Long page7;
    private Long page8;
    private Long page9;
    private Long page10;
    private Long page11;


}
