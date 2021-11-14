Delimiter $$

CREATE PROCEDURE buildTargetTables()
BEGIN

	set @counter = 1;

-- DECLARE counter int default 1;
-- DECLARE the_base varchar(10);
-- DECLARE the_currency varchar(10);

WHILE @counter <= (select count(distinct ticker) from currency) DO

	call targetArray;

	set @the_base = (select base from currency where id = 24);

	set @the_currency = (select ticker from TArray where ID = @counter);

	SET @DynamicSQL = CONCAT('CREATE TABLE ', @the_currency,'
	AS (select * from currency where ticker = ',"@the_currency",' AND base = ',"@the_base",');');

	select @DynamicSQL;

	prepare stmt from @DynamicSQL;

	EXECUTE stmt;

	DEALLOCATE PREPARE stmt;

	SET @counter = @counter + 1;

END WHILE;
END$$

Delimiter ;






	SET @DynamicSQL = 'CREATE TABLE Monkey
	 AS (select ticker, base, time, value from currency
	where ticker = "JPY" and base = "USD");';


	SET @DynamicSQL = 'CREATE TABLE ' + the_currency +
	' AS (select ticker, base, time, value from currency
	where ticker = '+ the_currency + ' and base = ' + the_base +');';

	SET @DynamicSQL = CONCAT('CREATE TABLE @the_currency AS (select * from currency where ticker = @the_currency, AND base = @the_base;');
