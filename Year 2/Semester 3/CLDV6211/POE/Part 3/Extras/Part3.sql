USE ST10467645_EventEase;

-- Creates the EventType lookup table.
-- This stores predefined event categories used to filter bookings.

CREATE TABLE EventType (
    EventTypeId INT PRIMARY KEY IDENTITY(1,1),
    EventTypeName VARCHAR(50) NOT NULL
);

-- Fills the EventType table with predefined categories so booking specialists can filter by event type immediately.
INSERT INTO EventType (EventTypeName)
VALUES
    ('Conference'),
    ('Wedding'),
    ('Birthday'),
    ('Corporate'),
    ('Concert'),
    ('Sports'),
    ('Exhibition'),
    ('Other');

-- Adding EventTypeId foreign key column to the existing Event table too link each event to a predefined category.
ALTER TABLE Event
ADD EventTypeId INT;

-- Creates the foreign key constraint linking Event to EventType.
ALTER TABLE Event
ADD CONSTRAINT FK_Event_EventType
FOREIGN KEY (EventTypeId) REFERENCES EventType(EventTypeId);

-- Drops and recreates the BookingSummary view.
-- Now includes EventType information so filtering by event type works in the Booking Summary search page.
DROP VIEW IF EXISTS BookingSummary;

GO

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
    e.EndDate,
    et.EventTypeId,
    et.EventTypeName
FROM Booking b
INNER JOIN Venue v ON b.VenueId = v.VenueId
INNER JOIN Event e ON b.EventId = e.EventId
LEFT JOIN EventType et ON e.EventTypeId = et.EventTypeId;

GO