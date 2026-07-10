package danielebulgaru.GestionePrenotazioni.repositories;

import danielebulgaru.GestionePrenotazioni.entities.Dipendente;
import danielebulgaru.GestionePrenotazioni.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    // Questo metodo servirà per impedire che un dipendente prenoti due viaggi nella stessa data
    Optional<Prenotazione> findByDipendenteAndDataRichiesta(Dipendente dipendente, LocalDate dataRichiesta);
}
