package danielebulgaru.GestionePrenotazioni.services;

import danielebulgaru.GestionePrenotazioni.payloads.DipendenteDTO;
import danielebulgaru.GestionePrenotazioni.entities.Dipendente;
import danielebulgaru.GestionePrenotazioni.exceptions.BadRequestException;
import danielebulgaru.GestionePrenotazioni.exceptions.NotFoundException;
import danielebulgaru.GestionePrenotazioni.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente save(DipendenteDTO body) {
        if (dipendenteRepository.findByEmail(body.email()).isPresent()) {
            throw new BadRequestException("L'email " + body.email() + " è già in uso!");
        }
        if (dipendenteRepository.findByUsername(body.username()).isPresent()) {
            throw new BadRequestException("Lo username " + body.username() + " è già in uso!");
        }
        // Trasformiamo il DTO in Entity per salvarlo
        Dipendente nuovo = new Dipendente(body.username(), body.nome(), body.cognome(), body.email());
        return dipendenteRepository.save(nuovo);
    }

    public List<Dipendente> findAll() { return dipendenteRepository.findAll(); }

    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dipendente con ID " + id + " non trovato!"));
    }

    public Dipendente findByIdAndUpdate(Long id, DipendenteDTO body) {
        Dipendente found = this.findById(id);

        if (!found.getEmail().equals(body.email()) && dipendenteRepository.findByEmail(body.email()).isPresent()) {
            throw new BadRequestException("La nuova email " + body.email() + " è già in uso!");
        }

        found.setUsername(body.username());
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setEmail(body.email());

        return dipendenteRepository.save(found);
    }

    public void findByIdAndDelete(Long id) {
        Dipendente found = this.findById(id);
        dipendenteRepository.delete(found);
    }
}
