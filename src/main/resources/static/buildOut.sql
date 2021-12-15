DELIMITER //

CREATE PROCEDURE buildOut()
BEGIN
	
set @counter =1;

	-- call targetArray;

set @entryCounter = (SELECT COUNT(*) FROM Exchange.currency);
	
	-- set @the_base = (select base from currency where id = (SELECT max(id) from Exchange.currency));
	
select @the_base;
		
	
loop_label: LOOP
IF @entryCounter < @counter THEN
	LEAVE loop_label;
	END IF;
	
		

	set @the_ticker = (select ticker from currency where id = @counter);
	
	select @the_ticker;

	set @the_base = (select base from currency where id = @counter);

	select @the_base;
	
	set @temp_table_string = CONCAT(@the_ticker,'_',@the_base);

	select @temp_table_string;

	set @tablePresent = 
	(SELECT count(*) FROM information_schema.tables
	WHERE table_schema = 'Exchange'
	AND table_name = @temp_table_string);
	
	
	IF @tablePresent = 1 THEN 
		SET @stringOne = CONCAT('INSERT INTO ',@temp_table_string,'
		SELECT * FROM currency WHERE ticker = ? AND base = ?;'); 

		prepare statementOne FROM @stringOne;
	
		execute statementOne USING @the_ticker, @the_base;
	
		
	ELSE
		SET @stringTwo = CONCAT('CREATE TABLE ',@temp_table_string,' AS 
		SELECT * FROM currency WHERE ticker = ? AND base = ?;');

		prepare statementTwo FROM @stringTwo;
	
		execute statementTwo USING @the_ticker, @the_base;
		
		
	END IF;

	set @counter = @counter + 1;

	set @tablePresent = 0;
	
END LOOP;

END //

DELIMITER ;