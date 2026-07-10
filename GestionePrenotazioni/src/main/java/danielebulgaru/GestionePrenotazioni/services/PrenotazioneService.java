package danielebulgaru.GestionePrenotazioni.services;

import danielebulgaru.GestionePrenotazioni.entities.Dipendente;
import danielebulgaru.GestionePrenotazioni.entities.Prenotazione;
import danielebulgaru.GestionePrenotazioni.entities.Viaggio;
import danielebulgaru.GestionePrenotazioni.exceptions.BadRequestException;
import danielebulgaru.GestionePrenotazioni.exceptions.NotFoundException;
import danielebulgaru.GestionePrenotazioni.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ViaggioService viaggioService;

    // 1. CREA UNA PRENOTAZIONE
    public Prenotazione save(Long dipendenteId, Long viaggioId, LocalDate dataRichiesta, String note) {
        // Cerca il dipendente e il viaggio usando i servizi esistenti (lanceranno errore se non trovati)
        Dipendente dipendente = dipendenteService.findById(dipendenteId);
        Viaggio viaggio = viaggioService.findById(viaggioId);

        // Il dipendente non può prenotare un viaggio per una data in cui è già occupato
        if (prenotazioneRepository.findByDipendenteAndDataRichiesta(dipendente, dataRichiesta).isPresent()) {
            throw new BadRequestException("Il dipendente " + dipendente.getNome() + " " + dipendente.getCognome() +
                    " ha già una prenotazione attiva per la data: " + dataRichiesta);
        }

        // Se supera il controllo, crea e salva la prenotazione
        Prenotazione nuovaPrenotazione = new Prenotazione(viaggio, dipendente, dataRichiesta, note);
        return prenotazioneRepository.save(nuovaPrenotazione);
    }

    // 2. RECUPERA TUTTE LE PRENOTAZIONI
    public List<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    // 3. RECUPERA UNA PRENOTAZIONE PER ID
    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione con ID " + id + " non trovata!"));
    }

    // 4. CANCELLA UNA PRENOTAZIONE
    public void findByIdAndDelete(Long id) {
        Prenotazione found = this.findById(id);
        prenotazioneRepository.delete(found);
    }
}