package danielebulgaru.GestionePrenotazioni.controllers;

import danielebulgaru.GestionePrenotazioni.payloads.ViaggioDTO;
import danielebulgaru.GestionePrenotazioni.entities.Viaggio;
import danielebulgaru.GestionePrenotazioni.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio createViaggio(@RequestBody @Validated ViaggioDTO body) {
        return viaggioService.save(body);
    }

    @GetMapping
    public List<Viaggio> getAllViaggi() { return viaggioService.findAll(); }

    @GetMapping("/{id}")
    public Viaggio getViaggioById(@PathVariable Long id) { return viaggioService.findById(id); }

    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable Long id, @RequestBody @Validated ViaggioDTO body) {
        return viaggioService.findByIdAndUpdate(id, body);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio updateStatoViaggio(@PathVariable Long id, @RequestBody String nuovoStato) {
        return viaggioService.updateStato(id, nuovoStato);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable Long id) { viaggioService.findByIdAndDelete(id); }
}
