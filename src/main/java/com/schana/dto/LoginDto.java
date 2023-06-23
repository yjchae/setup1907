package com.schana.dto;

import lombok.Builder;
import lombok.Data;

//@Getter
//@Setter
@Data
public class LoginDto {

    private String id;
    private String password;
    private String type;
}
