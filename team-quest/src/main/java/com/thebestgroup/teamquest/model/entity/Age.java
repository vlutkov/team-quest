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
public class Age {

    @Column(name = "minAge", nullable = false)
    private Short min;
    @Column(name = "maxAge")
    private Short max;
}
