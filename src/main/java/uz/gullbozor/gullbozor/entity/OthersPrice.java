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
@Table(name = "others_price")
public class OthersPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    private Double pvh60QvtLPrice;
    private Double pvh60TQvtPrice;
    private Double pvh60ZQvtPrice;
    private Double pvh60TrioShtapikTwoPrice;

    private Double shelf15Price;
    private Double shelf20Price;
    private Double shelf25Price;
    private Double shelf30Price;
    private Double shelf35Price;
    private Double shelf40Price;
    private Double shelf45Price;


    private Double aluLPrice;
    private Double aluTPrice;
    private Double aluZPrice;
    private Double aluShtapik2Price;
    private Double aluShtapik1Price;


    private Double pvh60QvtLPriceColor;
    private Double pvh60TQvtPriceColor;
    private Double pvh60ZQvtPriceColor;
    private Double pvh60TrioShtapikTwoPriceColor;

    private Double shelf15PriceColor;
    private Double shelf20PriceColor;
    private Double shelf25PriceColor;
    private Double shelf30PriceColor;
    private Double shelf35PriceColor;
    private Double shelf40PriceColor;
    private Double shelf45PriceColor;

    private Double lambriPvhPrice;
    private Double lambriPvhPriceColor;
    private Double lambriAluPrice;
    private Double lambriAluPriceColor;


    private Double aluLPriceColor;
    private Double aluTPriceColor;
    private Double aluZPriceColor;
    private Double aluShtapik2PriceColor;
    private Double aluShtapik1PriceColor;


    private Double oyna1Price;
    private Double oyna2Price;
    private Double oyna3Price;
    private Double oyna4Price;


    private Double ruchka155Price;
    private Double ruchka153Price;
    private Double ruchka155PriceColor;
    private Double ruchka153PriceColor;
    private Double petlyaPrice;
    private Double petlyaPriceColor;
    private Double saydinitelPrice;
    private Double saydinitelAluPrice;
    private Double chitPrice;
    private Double qoraBurchakPrice;
    private Double sariqBurchakPrice;
    private Double poroshokPrice;
    private Double tikolPrice;
    private Double rezinaPvhPrice;
    private Double rezinaBosmaPrice;
    private Double rezinaYuPrice;
    private Double samarezPrice;
    private Double ispandiletPrice;
    private Double sulchikPrice;
    private Double sulchikPriceColor;
    private Double ilmoqPrice;
    private Double ilmoqPriceColor;
    private Double zamok155MiniPrice;
    private Double zamok155MaxPrice;
    private Double zamok153Price;
    private Double archaMiniPrice;
    private Double archaBalkonPrice;
    private Double ikkitalikMexanizmPrice;






}
