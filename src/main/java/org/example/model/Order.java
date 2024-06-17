package org.example.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String id;
    private String cusId;
    private String status;
    private Date date;
    private Double amount;
    private String empId;
}
