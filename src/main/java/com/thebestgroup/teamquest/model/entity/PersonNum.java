package com.thebestgroup.teamquest.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class PersonNum {

    @Column(name = "minPersonNum", nullable = false)
    private Short min;
    @Column(name = "maxPersonNum", nullable = false)
    private Short max;
}
