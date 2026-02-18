package co.edu.uniandes.dse.TallerPersistencia.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;


@Data
@Entity

public class DirectorEntity extends BaseEntity{

    public String nombre;
    public String biografia;

    @OneToMany
    public List<PeliculaEntity> peliculas;
    


}
