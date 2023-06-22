package com.schana.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name ="vw_assemblyinfo")
public class AssemblyInfoEntity {

    @Id
    private Integer peopletotal;
    private Integer adulttotal;
    private Integer midhightotal;
    private Integer chototal;
    private Integer babytotal;

    private Integer useroomtotal;
    private Integer todaypeople;
    private Integer useroom;
    private Integer adult;
    private Integer midhigh;
    private Integer cho;
    private Integer baby;
    private Integer emptyroom;

}
