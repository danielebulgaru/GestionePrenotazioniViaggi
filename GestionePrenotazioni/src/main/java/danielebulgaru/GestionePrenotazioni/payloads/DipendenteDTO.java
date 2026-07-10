package danielebulgaru.GestionePrenotazioni.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DipendenteDTO(
        @NotBlank(message = "Lo username è obbligatorio")
        String username,

        @NotBlank(message = "Il nome è obbligatorio")
        String nome,

        @NotBlank(message = "Il cognome è obbligatorio")
        String cognome,

        @NotBlank(message = "L'email è obbligatoria")
        @Email(message = "Inserisci un indirizzo email valido")
        String email
) {}
