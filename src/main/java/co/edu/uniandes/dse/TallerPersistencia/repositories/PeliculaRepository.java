package co.edu.uniandes.dse.TallerPersistencia.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;

import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {

    Optional<PeliculaEntity> findByNombre(String nombre);
    
    
}
