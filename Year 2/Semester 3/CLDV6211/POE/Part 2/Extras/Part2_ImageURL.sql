USE ST10467645_EventEase;

-- Updating the ImageUrl columns to allow NULL values.
-- In Part 1, image URLs were stored as text links, but in Part 2
-- images are uploaded using Azurite blob storage.
-- NULL values are needed so existing data can be cleared
-- and replaced with new blob URLs from the application.

ALTER TABLE Venue 
ALTER COLUMN ImageUrl NVARCHAR(MAX) NULL;

ALTER TABLE Event 
ALTER COLUMN ImageUrl NVARCHAR(MAX) NULL;

-- Clearing old placeholder URLs to prevent broken images
-- and to ensure new uploads are stored correctly in blob storage.

UPDATE Venue 
SET ImageUrl = NULL 
WHERE ImageUrl IS NOT NULL;

UPDATE Event 
SET ImageUrl = NULL 
WHERE ImageUrl IS NOT NULL;

SELECT VenueId, VenueName, ImageUrl FROM Venue;