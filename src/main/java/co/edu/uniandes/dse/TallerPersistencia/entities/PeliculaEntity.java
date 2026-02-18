package co.edu.uniandes.dse.TallerPersistencia.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.util.List;



@Data
@Entity

public class PeliculaEntity extends BaseEntity {

    public String titulo;
    public Integer anioLanzamiento;

    @ManyToMany
    public List<ActorEntity> actores;

    @ManyToOne
    public DirectorEntity director;






}
