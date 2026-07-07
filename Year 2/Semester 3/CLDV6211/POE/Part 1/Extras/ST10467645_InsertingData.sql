USE ST10467645_EventEase;

-- Insert sample Venues
INSERT INTO Venue (VenueName, Location, Capacity, ImageURL)
VALUES 
('Grand Hall', 'Johannesburg', 500, '	https://phgcdn.com/images/uploads/STLUS/masthead/hero_grandhall.png'),
('Garden Pavilion', 'Cape Town', 200, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_3HuQxqcl_sIC3iU_szQPWd7xzq8aRckb0A&s');

-- Insert sample Events
INSERT INTO Event (EventName, StartDate, EndDate, ImageURL)
VALUES 
('Annual Gala', '2026-04-01', '2026-04-01', '	https://images.quicket.co.za/0547874_0.jpeg'),
('Tech Conference', '2026-05-15', '2026-05-16', 'https://www.techrepublic.com/wp-content/uploads/2022/05/top-5-conferences-2022-tom.jpeg');


SELECT VenueID FROM Venue;

SELECT EventID FROM Event;

-- Insert sample Bookings
INSERT INTO Booking (VenueID, EventID, BookingDate)
VALUES 
(6, 6, '2026-03-15'),
(7, 7, '2026-03-20');