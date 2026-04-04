package com.edudev.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    private String name;
    private String documentNumber;
    private String documentType;
    private String address;
    private String email;

}
