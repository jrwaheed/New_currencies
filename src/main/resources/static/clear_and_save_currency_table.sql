DELIMITER //

CREATE PROCEDURE clear_and_save_currency_table() ()
BEGIN
	
-- INSERT INTO Currency_History 
-- SELECT * FROM currency;

TRUNCATE TABLE currency ;
	
END //

DELIMITER ;



