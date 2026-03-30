package recifecultural.schedule.domain;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Event {
    private final UUID id;
    private UUID eventOwner;
    private UUID eventLocation;

    private String title;
    private String shortDescription;
    private String longDescription;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private final List<LocalDateTime> presentationDates;

    private URI ticketUri;

    private BigDecimal fullTicketPrice;
    private BigDecimal halfTicketPrice;
    private String socialTicketPrice;

    public Event(
            UUID eventOwner,
            UUID eventLocation,
            String title,
            String shortDescription,
            String longDescription,
            LocalDateTime startDate,
            LocalDateTime endDate,
            URI ticketUri,
            BigDecimal fullTicketPrice,
            BigDecimal halfTicketPrice,
            String socialTicketPrice
    ) {
        this.id = UUID.randomUUID();
        this.eventOwner = eventOwner;
        this.eventLocation = eventLocation;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;

        validateDates(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;

        this.presentationDates = new ArrayList<>();
        this.ticketUri = ticketUri;

        validatePrices(fullTicketPrice, halfTicketPrice);
        this.fullTicketPrice = fullTicketPrice;
        this.halfTicketPrice = halfTicketPrice;
        this.socialTicketPrice = socialTicketPrice;
    }

    public void addPresentationDate(LocalDateTime dateTime) {
        if (dateTime == null) throw new IllegalArgumentException("Data de apresentação não pode ser nula.");
        if (dateTime.isBefore(startDate) || dateTime.isAfter(endDate)) {
            throw new IllegalArgumentException("A data deve estar entre a data de início e a de fim do evento.");
        }
        this.presentationDates.add(dateTime);
    }

    public void removePresentationDate(LocalDateTime dateTime) {
        this.presentationDates.remove(dateTime);
    }

    public boolean isSinglePresentation() {
        return this.presentationDates.size() == 1;
    }

    public void updatePricing(BigDecimal fullPrice, BigDecimal halfPrice, String socialPrice) {
        validatePrices(fullPrice, halfPrice);
        this.fullTicketPrice = fullPrice;
        this.halfTicketPrice = halfPrice;
        this.socialTicketPrice = socialPrice;
    }

    private void validateDates(LocalDateTime start, LocalDateTime end) {
        if (start != null && end != null && end.isBefore(start)) {
            throw new IllegalArgumentException("A data de fim não pode ser antes da data de início do evento.");
        }
    }

    private void validatePrices(BigDecimal full, BigDecimal half) {
        if (full != null && full.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("O preço da entrada não pode ser negativo.");
        if (half != null && half.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("O preço da meia entrada não pode ser negativo.");
    }

    public UUID getId() { return id; }
    public UUID getEventOwner() { return eventOwner; }
    public UUID getEventLocation() { return eventLocation; }
    public String getTitle() { return title; }
    public String getShortDescription() { return shortDescription; }
    public String getLongDescription() { return longDescription; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public URI getTicketUri() { return ticketUri; }
    public BigDecimal getFullTicketPrice() { return fullTicketPrice; }
    public BigDecimal getHalfTicketPrice() { return halfTicketPrice; }
    public String getSocialTicketPrice() { return socialTicketPrice; }

    public List<LocalDateTime> getPresentationDates() {
        return Collections.unmodifiableList(presentationDates);
    }
}