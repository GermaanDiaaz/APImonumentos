package com.apimonumentos.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monument{
    @Id @GeneratedValue
    private Long id;
    private String nombrePais;
    private String ciudad;
    private String localizacion;
    private String nombreMonumento;
    private String descripcion;
    private String url;

}
