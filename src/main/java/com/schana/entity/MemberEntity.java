package com.schana.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="tb_member")
public class MemberEntity {

    @Id
    private String id;
    private String password;
    private String typename;

}
