package danielebulgaru.GestionePrenotazioni.services;

import danielebulgaru.GestionePrenotazioni.payloads.ViaggioDTO;
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

    public Viaggio save(ViaggioDTO body) {
        Viaggio nuovo = new Viaggio(body.destinazione(), body.data());
        return viaggioRepository.save(nuovo);
    }

    public List<Viaggio> findAll() { return viaggioRepository.findAll(); }

    public Viaggio findById(Long id) {
        return viaggioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Viaggio con ID " + id + " non trovato!"));
    }

    public Viaggio findByIdAndUpdate(Long id, ViaggioDTO body) {
        Viaggio found = this.findById(id);
        found.setDestinazione(body.destinazione());
        found.setData(body.data());
        return viaggioRepository.save(found);
    }

    public void findByIdAndDelete(Long id) {
        Viaggio found = this.findById(id);
        viaggioRepository.delete(found);
    }

    public Viaggio updateStato(Long id, String nuovoStato) {
        Viaggio found = this.findById(id);
        found.setStato(nuovoStato.toUpperCase());
        return viaggioRepository.save(found);
    }
}