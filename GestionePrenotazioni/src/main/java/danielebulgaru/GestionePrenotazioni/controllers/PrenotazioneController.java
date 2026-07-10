package danielebulgaru.GestionePrenotazioni.controllers;

import danielebulgaru.GestionePrenotazioni.payloads.PrenotazioneDTO;
import danielebulgaru.GestionePrenotazioni.entities.Prenotazione;
import danielebulgaru.GestionePrenotazioni.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(@RequestBody @Validated PrenotazioneDTO body) {
        return prenotazioneService.save(body);
    }

    @GetMapping
    public List<Prenotazione> getAllPrenotazioni() { return prenotazioneService.findAll(); }

    @GetMapping("/{id}")
    public Prenotazione getPrenotazioneById(@PathVariable Long id) { return prenotazioneService.findById(id); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable Long id) { prenotazioneService.findByIdAndDelete(id); }
}
