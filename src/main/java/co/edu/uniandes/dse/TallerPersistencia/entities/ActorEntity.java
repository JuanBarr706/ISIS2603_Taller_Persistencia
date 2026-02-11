package co.edu.uniandes.dse.TallerPersistencia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;


@Data
@Entity

public class ActorEntity extends BaseEntity{

    public String nombre;
    public String nacionalidad;

    @ManyToMany
    public PeliculaEntity pelicula;







}
