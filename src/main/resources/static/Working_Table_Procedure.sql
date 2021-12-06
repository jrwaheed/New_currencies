CREATE DEFINER=`root`@`localhost` PROCEDURE `Exchange`.`BuildOrUpdateTables`()
BEGIN
	set @counter =1;

	call targetArray;
	
	set @the_base = (select base from currency where id = (SELECT max(id) from Exchange.currency));
		
	set @the_currency = (select ticker from TArray where ID = @counter);

	set @temp_table_string = CONCAT(@the_currency,'_',@the_base);


	set @tablePresent = 
	(SELECT count(*) FROM information_schema.tables
	WHERE table_schema = 'Exchange'
	AND table_name = @temp_table_string);



	
	
	IF @tablePresent = 1 THEN 
		SET @stringOne = CONCAT('INSERT INTO ',@temp_table_string,'
		SELECT * FROM currency WHERE ticker = ? AND base = ?;'); 

		prepare statementOne FROM @stringOne;
	
		execute statementOne USING @the_currency, @the_base;
	
		
	ELSE
		SET @stringTwo = CONCAT('CREATE TABLE ',@temp_table_string,' AS 
		SELECT * FROM currency WHERE ticker = ? AND base = ?;');

		prepare statementTwo FROM @stringTwo;
	
		execute statementTwo USING @the_currency, @the_base;
		
		
	END IF;

	 
END



