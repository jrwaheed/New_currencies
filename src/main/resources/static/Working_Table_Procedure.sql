Delimiter $$
Drop procedure if exists buildTargetTables$$
CREATE PROCEDURE buildTargetTables()
BEGIN
	DECLARE counter INT;
	SET counter = 0;

	WHILE counter <= (select count(distinct ticker)	from currency c) DO
 		SET counter = counter + 1;

 		call targetArray;

 		select @theBase:= base
		from currency c where id = 14;

		select @theCurrency:= ticker
		from TArray t where ID = @counter;


		SET @DynamicSQL = 'CREATE TABLE ' + @theCurrency +
		' AS (select ticker, base, time, value from currency c
				where ticker = '+ @theCurrency + ' and base = ' + @theBase +');';

		prepare stmt from @DynamicSQL;


		EXECUTE stmt;

	END WHILE;

END $$

Delimiter ;








Delimiter //
CREATE PROCEDURE buildTargetTables()
BEGIN
	declare counter int default 1;
	DECLARE theBase varchar(10);
	DECLARE theCurrency varchar(10);
	WHILE counter <= (select count(distinct ticker) from currency c) DO 		
		call targetArray;
	
		
		set theBase = (base from currency where id = 24);

		
		set theCurrency = (ticker from TArray where ID = counter);

		SET @DynamicSQL = 'CREATE TABLE ' + theCurrency + 
		' AS (select ticker, base, time, value from currency c 
		where ticker = '+ theCurrency + ' and base = ' + theBase +');';
	
		prepare stmt from @DynamicSQL;
		EXECUTE stmt;
		DEALLOCATE PREPARE stmt;
		SET counter = counter + 1;
	END WHILE;
END//
Delimiter ;



Delimiter //
CREATE PROCEDURE buildAndDeleteTargetTables()
BEGIN
	Drop procedure if exists buildTargetTables;
	buildTargetTables()
END//
Delimiter ;