package recifecultural.schedule.domain;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Event {
    private final UUID id = UUID.randomUUID();

    private UUID eventOwner

    private boolean isSinglePresentation;

    private LocalDateTime startDate; // start date for a not single presentation
    private LocalDateTime endDate;   // end date for a not single presentation

    private List<LocalDateTime> presentationDates; // e.g. seg 14:00, ter 17:00

    private String shortDescription;
    private String longDescription;

    private URI ticketUri;

    private BigDecimal fullTicketPrice;
    private BigDecimal halfTicketPrice;
    private String socialTicketPrice;

     public Event(
             UUID eventOwner,
             boolean isSinglePresentation,
             LocalDateTime startDate,
             LocalDateTime endDate,
             List<LocalDateTime> presentationDates,
             String shortDescription,
             String longDescription,
             URI ticketUri,
             BigDecimal fullTicketPrice,
             BigDecimal halfTicketPrice,
             String socialTicketPrice
     ) {
         this.eventOwner = eventOwner;
         this.isSinglePresentation = isSinglePresentation;
         this.startDate = startDate;
         this.endDate = endDate;
         this.presentationDates = isSinglePresentation ? presentationDates : null;
         this.shortDescription = shortDescription;
         this.longDescription = longDescription;
         this.ticketUri = ticketUri;
         this.fullTicketPrice = fullTicketPrice;
         this.halfTicketPrice = halfTicketPrice;
         this.socialTicketPrice = socialTicketPrice;
     }
}