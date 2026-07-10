package danielebulgaru.GestionePrenotazioni.services;

import danielebulgaru.GestionePrenotazioni.payloads.PrenotazioneDTO;
import danielebulgaru.GestionePrenotazioni.entities.Dipendente;
import danielebulgaru.GestionePrenotazioni.entities.Prenotazione;
import danielebulgaru.GestionePrenotazioni.entities.Viaggio;
import danielebulgaru.GestionePrenotazioni.exceptions.BadRequestException;
import danielebulgaru.GestionePrenotazioni.exceptions.NotFoundException;
import danielebulgaru.GestionePrenotazioni.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private ViaggioService viaggioService;

    public Prenotazione save(PrenotazioneDTO body) {
        Dipendente dipendente = dipendenteService.findById(body.dipendenteId());
        Viaggio viaggio = viaggioService.findById(body.viaggioId());

        if (prenotazioneRepository.findByDipendenteAndDataRichiesta(dipendente, body.dataRichiesta()).isPresent()) {
            throw new BadRequestException("Il dipendente " + dipendente.getNome() + " " + dipendente.getCognome() +
                    " ha già una prenotazione attiva per la data: " + body.dataRichiesta());
        }

        Prenotazione nuovaPrenotazione = new Prenotazione(viaggio, dipendente, body.dataRichiesta(), body.note());
        return prenotazioneRepository.save(nuovaPrenotazione);
    }

    public List<Prenotazione> findAll() { return prenotazioneRepository.findAll(); }

    public Prenotazione findById(Long id) {
        return prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione con ID " + id + " non trovata!"));
    }

    public void findByIdAndDelete(Long id) {
        Prenotazione found = this.findById(id);
        prenotazioneRepository.delete(found);
    }
}