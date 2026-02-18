package co.edu.uniandes.dse.TallerPersistencia.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;
import co.edu.uniandes.dse.TallerPersistencia.repositories.PeliculaRepository;



@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public PeliculaEntity crearPelicula(PeliculaEntity pelicula) throws IllegalArgumentException {

        
              if (pelicula.getTitulo() == null || pelicula.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la película no puede estar vacío");
        }

        
        if (peliculaRepository.findByNombre(pelicula.getTitulo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe una película con ese nombre");
        }

        
        if (pelicula.getAnioLanzamiento() <= 1930) {
            throw new IllegalArgumentException("El año de la película debe ser mayor a 1930");
        }

        return peliculaRepository.save(pelicula);
    }



}
