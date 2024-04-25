package com.projetoaps.demoprojetoaps.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter 
@Entity
@Table(name = "covid_dados")
public class Covid implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    @NotNull(message = "{NotNull.Covid.local}")
    @Enumerated(EnumType.STRING)
    private Local local;

    @Column(name = "casos", nullable = false)
    @NotNull(message = "{NotNull.Covid.qtCasos}")
    private Long qtCasos;

    @Column(name = "obitos", nullable = false)
    @NotNull(message = "{NotNull.Covid.qtObitos}")
    private Long qtObitos;

    @Column(nullable = false, length = 3)
    @NotNull(message = "{NotNull.Covid.semana}")
    private Integer semana;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Covid other = (Covid) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}
