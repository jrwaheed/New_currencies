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
	
		declare @DynamicSQL AS nvarchar(max);
	
		select @DynamicSQL = 'CREATE TABLE ' + @theCurrency + 
		' AS (select ticker, base, time, value from currency c 
				where ticker = '+ @theCurrency + ' and base = ' + @theBase +');'
			
				
		EXEC(@DynamicSQL);
	END WHILE;

END $$

Delimiter ;