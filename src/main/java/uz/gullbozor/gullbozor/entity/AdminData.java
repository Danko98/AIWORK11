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
@Table(name = "admin_data")
public class AdminData {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long generalCount;
    private Byte typeNumber;
    private String typeName;
    private String monthName;
    private Short year;
    private Short month;


    private Short day1;
    private Short day2;
    private Short day3;
    private Short day4;
    private Short day5;
    private Short day6;
    private Short day7;
    private Short day8;
    private Short day9;
    private Short day10;
    private Short day11;
    private Short day12;
    private Short day13;
    private Short day14;
    private Short day15;
    private Short day16;
    private Short day17;
    private Short day18;
    private Short day19;
    private Short day20;
    private Short day21;
    private Short day22;
    private Short day23;
    private Short day24;
    private Short day25;
    private Short day26;
    private Short day27;
    private Short day28;
    private Short day29;
    private Short day30;
    private Short day31;



}
