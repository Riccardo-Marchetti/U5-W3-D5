package riccardo.U5W3D5.payloads;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime dateMessage) {
}
