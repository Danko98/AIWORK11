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
@Table(name = "akfa_price")
public class AkfaPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double pvh60QvtL;
    private Double pvh60TQvt;
    private Double pvh60ZQvt;
    private Double pvh60TrioShtapikTwo;

    private Double pvhLambri1;
    private Double pvhLambri2;

    private Double shelf15;
    private Double shelf20;
    private Double shelf25;
    private Double shelf30;
    private Double shelf35;
    private Double shelf40;
    private Double shelf45;





    private Double aluL;
    private Double aluT;
    private Double aluZ;
    private Double aluShtapik2;
    private Double aluShtapik1;







}
