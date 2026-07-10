package danielebulgaru.GestionePrenotazioni.services;

import danielebulgaru.GestionePrenotazioni.entities.Viaggio;
import danielebulgaru.GestionePrenotazioni.exceptions.NotFoundException;
import danielebulgaru.GestionePrenotazioni.repositories.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;

    // 1. CREA UN NUOVO VIAGGIO
    public Viaggio save(Viaggio body) {
        // Lo stato parte in automatico come "IN_PROGRAMMA" grazie al valore di default nell'entità
        return viaggioRepository.save(body);
    }

    // 2. RECUPERA TUTTI I VIAGGI
    public List<Viaggio> findAll() {
        return viaggioRepository.findAll();
    }

    // 3. RECUPERA UN VIAGGIO PER ID
    public Viaggio findById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio con ID " + id + " non trovato!"));
    }

    // 4. MODIFICA UN VIAGGIO
    public Viaggio findByIdAndUpdate(Long id, Viaggio body) {
        Viaggio found = this.findById(id);

        found.setDestinazione(body.getDestinazione());
        found.setData(body.getData());
        found.setStato(body.getStato()); // Permette anche di cambiare lo stato

        return viaggioRepository.save(found);
    }

    // 5. CANCELLA UN VIAGGIO
    public void findByIdAndDelete(Long id) {
        Viaggio found = this.findById(id);
        viaggioRepository.delete(found);
    }

    // 6. METODO SPECIFICO PER MODIFICARE SOLO LO STATO
    public Viaggio updateStato(Long id, String nuovoStato) {
        Viaggio found = this.findById(id);
        found.setStato(nuovoStato.toUpperCase());
        return viaggioRepository.save(found);
    }
}