Delimiter $$

CREATE PROCEDURE buildOrUpdateTargetTables()
BEGIN

	set @counter = 1;

-- DECLARE counter int default 1;
-- DECLARE the_base varchar(10);
-- DECLARE the_currency varchar(10);

WHILE @counter <= (select count(distinct ticker) from currency) DO 	

	SET @counter = @counter + 1;

	call targetArray;

	set @the_base = (select base from currency where id = (SELECT max(id) from Exchange.currency));
	
	set @the_currency = (select ticker from TArray where ID = @counter);


	SET @DynamicCheck = CONCAT('IF EXISTS ' ,@the_currency,'_',@the_base, ' THEN;');
	
	SET @DynamicCreate = CONCAT('CREATE TABLE ',@the_currency,'_',@the_base,'
	AS (select * from currency where ticker = ',"@the_currency",' AND base = ',"@the_base",');');

	SET @DynamicUpdate = CONCAT(
	'SELECT * FROM ',@the_currency,'_',@the_base,
	' UNION ALL SELECT AS (select * from currency where ticker = ',"@the_currency",' AND base = ',"@the_base",');');

	

	prepare createstmt from @DynamicCreate;

	prepare checkstmt from @DynamicCheck;

	prepare updatestmt from @DynamicUpdate;


	IF EXIST CONCAT('DROP TABLE IF EXISTS ' ,@the_currency,'_',@the_base, ';') THEN 
		
	

	EXECUTE dropstmt;

	DEALLOCATE PREPARE dropstmt; 

	EXECUTE stmt;

	DEALLOCATE PREPARE stmt;

	

END WHILE;




END$$

Delimiter ;