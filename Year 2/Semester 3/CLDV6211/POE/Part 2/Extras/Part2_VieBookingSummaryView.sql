-- ADDED FOR PART 2 - creates a database view that joins all 3 tables.
-- This view consolidates Booking, Venue, and Event data into one result set.
-- Using a view means we query one object instead of writing JOIN logic every time the combined booking information displayed is needed.
CREATE VIEW BookingSummary AS
SELECT 
    b.BookingId,
    b.BookingDate,
    v.VenueId,
    v.VenueName,
    v.Location,
    v.Capacity,
    e.EventId,
    e.EventName,
    e.StartDate,
    e.EndDate
FROM Booking b
INNER JOIN Venue v ON b.VenueId = v.VenueId
INNER JOIN Event e ON b.EventId = e.EventId;

