package org.example.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order")
@Table(name = "order")
public class OrderEntity {

    @Id
    private String id;
    private String cusId;
    private String status;
    private Date date;
    private double amount;
}
