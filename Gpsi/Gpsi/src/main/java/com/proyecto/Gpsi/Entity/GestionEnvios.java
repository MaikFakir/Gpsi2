package com.proyecto.Gpsi.Entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name ="gestion_de_paquetes_envios")
public class GestionEnvios implements Serializable {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
	@JoinColumn(name="usuarios_id")
	private Usuario usuario;


    @Column(nullable = false, length = 45)
    private String numGuia;

    @Column(nullable = false, length = 50)
    private String destino;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false, length = 50)
    private String estado;

    @Column(nullable = false, length = 50)
    private String tipoEnvio;

    @Column(nullable = false, length = 50)
    private String tipoVehiculo;

    @Column(nullable = false, length = 50)
    private String tamPaquete;

    
    @Column(name = "fecha_creacion" ,updatable = false, nullable = false)
    private String fechacreacion;

    
    @Column(name = "fecha_recibido" , nullable = false)
    private String fechaRecibido;

    @ManyToOne
	@JoinColumn(name="tipo_productos_id")
	private TipoProd tipoProd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumGuia() {
        return numGuia;
    }

    public void setNumGuia(String numGuia) {
        this.numGuia = numGuia;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(String fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public TipoProd getTipoProd() {
        return tipoProd;
    }

    public void setTipoProd(TipoProd tipoProd) {
        this.tipoProd = tipoProd;
    }

    public String getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(String tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getTamPaquete() {
        return tamPaquete;
    }

    public void setTamPaquete(String tamPaquete) {
        this.tamPaquete = tamPaquete;
    }

    


}
