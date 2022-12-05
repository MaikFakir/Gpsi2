package com.proyecto.Gpsi.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="seguimiento")
public class Seguimiento implements Serializable {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private Integer num_Intentos;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false, length = 500)
    private String novedades;

    @ManyToOne
	@JoinColumn(name="Gestion_de_envios_id")
	private GestionEnvios gestionenvios;

    @ManyToOne
	@JoinColumn(name="Enrutador_id")
	private Enrutador enrutador;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum_Intentos() {
        return num_Intentos;
    }

    public void setNum_Intentos(Integer num_Intentos) {
        this.num_Intentos = num_Intentos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNovedades() {
        return novedades;
    }

    public void setNovedades(String novedades) {
        this.novedades = novedades;
    }



    public Enrutador getEnrutador() {
        return enrutador;
    }

    public void setEnrutador(Enrutador enrutador) {
        this.enrutador = enrutador;
    }

    public GestionEnvios getGestionenvios() {
        return gestionenvios;
    }

    public void setGestionenvios(GestionEnvios gestionenvios) {
        this.gestionenvios = gestionenvios;
    }

    
    
}
