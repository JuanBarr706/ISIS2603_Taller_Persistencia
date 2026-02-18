package co.edu.uniandes.dse.TallerPersistencia.services;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.uniandes.dse.TallerPersistencia.entities.PeliculaEntity;
import co.edu.uniandes.dse.TallerPersistencia.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.TallerPersistencia.repositories.PeliculaRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class PeliculaServiceTest {

    // objeto utilizado en las distintas pruebas
    private PeliculaEntity pelicula;

    // servicio real inyectado por Spring
    @org.springframework.beans.factory.annotation.Autowired
    private PeliculaService peliculaService;

    // repositorio simulado que Spring gestiona como mock
    @org.springframework.boot.test.mock.mockito.MockBean
    private PeliculaRepository peliculaRepository;

    @BeforeEach
    void setUp() {
        pelicula = new PeliculaEntity();
        pelicula.setTitulo("Interstellar");
        pelicula.setAnioLanzamiento(2014);
    }

    @Test
    void crearPelicula_ok() throws IllegalOperationException {
        when(peliculaRepository.findByTitulo("Interstellar")).thenReturn(Optional.empty());
        when(peliculaRepository.save(any(PeliculaEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        PeliculaEntity creada = peliculaService.crearPelicula(pelicula);

        assertNotNull(creada);
        assertEquals("Interstellar", creada.getTitulo());
        assertEquals(2014, creada.getAnioLanzamiento());
        verify(peliculaRepository).save(pelicula);
    }

    @Test
    void crearPelicula_tituloVacio_lanzaExcepcion() {
        pelicula.setTitulo("   ");

        assertThrows(IllegalOperationException.class, () -> peliculaService.crearPelicula(pelicula));
        verify(peliculaRepository, never()).save(any());
    }

    @Test
    void crearPelicula_tituloDuplicado_lanzaExcepcion() {
        when(peliculaRepository.findByTitulo("Interstellar"))
                .thenReturn(Optional.of(new PeliculaEntity()));

        assertThrows(IllegalOperationException.class, () -> peliculaService.crearPelicula(pelicula));
        verify(peliculaRepository, never()).save(any());
    }

    @Test
    void crearPelicula_anioInvalido_lanzaExcepcion() {
        pelicula.setAnioLanzamiento(1930);

        assertThrows(IllegalOperationException.class, () -> peliculaService.crearPelicula(pelicula));
        verify(peliculaRepository, never()).save(any());
    }

    // prueba explícita para el caso año 1900 solicitado
    @Test
    void crearPelicula_anio1900_lanzaExcepcion() {
        pelicula.setAnioLanzamiento(1900);

        assertThrows(IllegalOperationException.class, () -> peliculaService.crearPelicula(pelicula));
        verify(peliculaRepository, never()).save(any());
    }
}
