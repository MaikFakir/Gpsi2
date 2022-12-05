package com.proyecto.Gpsi.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="enrutador")
public class Enrutador {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_ruta" , nullable = false)
    private String fechaRuta;

    @Column(nullable = false, length = 50)
    private String direccion;

    @Column(name = "complemento_Direccion" ,nullable = false, length = 50)
    private String complementoDireccion;

    @Column(nullable = false, length = 500)
    private String instrucciones;

    @Column(nullable = false, length = 50)
    private String peso;

    @ManyToOne
	@JoinColumn(name="Gestion_envios_id")
	private GestionEnvios gestionenvios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaRuta() {
        return fechaRuta;
    }

    public void setFechaRuta(String fechaRuta) {
        this.fechaRuta = fechaRuta;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComplementoDireccion() {
        return complementoDireccion;
    }

    public void setComplementoDireccion(String complementoDireccion) {
        this.complementoDireccion = complementoDireccion;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public GestionEnvios getGestionenvios() {
        return gestionenvios;
    }

    public void setGestionenvios(GestionEnvios gestionenvios) {
        this.gestionenvios = gestionenvios;
    }


    

}
