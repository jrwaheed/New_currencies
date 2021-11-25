Delimiter $$

CREATE PROCEDURE createOrUpdateTargetTables()
BEGIN

	set @counter = 1;

	WHILE @counter <= (select count(distinct ticker) from currency) DO 	
	
		SET @counter = @counter + 1;
	
		call targetArray;
	
		set @the_base = (select base from currency where id = (SELECT max(id) from Exchange.currency));
		
		set @the_currency = (select ticker from TArray where ID = @counter);
	
		
		SET @DynamicCreateOrUpdate = CONCAT(
		'IF EXISTS (SELECT * FROM ',@the_currency,'_',@the_base,') 
		BEGIN
			SELECT * FROM ',@the_currency,'_',@the_base,' 
			UNION ALL 
			SELECT * FROM currency WHERE ticker = ',"@the_currency",' AND base = ',"@the_base",';
		END
		ELSE
		BEGIN
			CREATE TABLE ',@the_currency,'_',@the_base,'
			AS (SELECT * FROM currency WHERE ticker = ',"@the_currency",' AND base = ',"@the_base",');
		END 
		END;');

		prepare createOrUpdatestmt from @DynamicCreateOrUpdate;
	
		EXECUTE createOrUpdatestmt;
	
		DEALLOCATE PREPARE createOrUpdatestmt; 
	
	
	END WHILE;

	call clear_and_save_currency_table(); 

END$$

Delimiter ;






