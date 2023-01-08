package com.softcode.rabbitmqmodel;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DutyChangeEvent implements Serializable {

    private Integer employeeDutyId;
    private DutyChangeType dutyChangeType;

}
