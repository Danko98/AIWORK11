package uz.gullbozor.gullbozor.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_body_alu_win")
public class OrderBodyAluWin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderHeadId;
    private Integer width;
    private Integer height;
    private Integer aluL;
    private Integer aluT;
    private Integer aluZ;
    private Integer shtapik1;
    private Integer shtapik2;
    private Integer shelf_length;
    private Integer categoryNum;
    private Byte shelf_size;
    private Byte color;
    private Byte glassTypeNum;
    private Byte ruchka;
    private Byte petlya;
    private Byte saydinitel;
    private Byte qoraBurchak;
    private Byte sariqBurchak;
    private Byte ispandiletCount;
    private Byte archa;
    private Short poroshok;
    private Short tikol;
    private Short samarez;
    private Double glassKvadrat;
    private Boolean glassTwoFloor;
    private Double chit;
    private Double rezinaYu;
    private Double rezinaBosma;


    private Double shelfSum;
    private Double glassSum;
    private Double pvhLSum;
    private Double pvhTSum;
    private Double pvhZSum;
    private Double shtapik1Sum;
    private Double shtapik2Sum;
    private Double ruchkaSum;
    private Double petlyaSum;
    private Double saydinitelSum;
    private Double chitSum;
    private Double archaSum;
    private Double qoraBurchakSum;
    private Double sariqBurchakSum;
    private Double poroshokSum;
    private Double tikolSum;
    private Double rezinaPvhSum;
    private Double samarezSum;
    private Double ispandiletSum;


}
