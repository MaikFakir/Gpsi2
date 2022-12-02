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
@Table(name = "bitacora_operaciones")
public class BitacoraOperaciones {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="gestion_de_pruebas_envios_id")
	private GestionEnvios gestionEnvios;

    @Column(nullable = false, length = 50)
    private String numGuia;

    @Column(nullable = false, length = 50)
    private String Orden;

    @Column(nullable = false, length = 50)
    private Integer valorSeguroProducto;

    @Column(nullable = false, length = 50)
    private String estado;
    
    @Column(nullable = false, length = 50)
    private String distancia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GestionEnvios getGestionEnvios() {
        return gestionEnvios;
    }

    public void setGestionEnvios(GestionEnvios gestionEnvios) {
        this.gestionEnvios = gestionEnvios;
    }

    public String getNumGuia() {
        return numGuia;
    }

    public void setNumGuia(String numGuia) {
        this.numGuia = numGuia;
    }

    public String getOrden() {
        return Orden;
    }

    public void setOrden(String orden) {
        Orden = orden;
    }

    public Integer getValorSeguroProducto() {
        return valorSeguroProducto;
    }

    public void setValorSeguroProducto(Integer valorSeguroProducto) {
        this.valorSeguroProducto = valorSeguroProducto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    







    
    
}
