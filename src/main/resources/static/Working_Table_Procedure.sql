Delimiter $$
CREATE PROCEDURE buildTargetTables()
BEGIN
DECLARE counter int default 1;
DECLARE the_base varchar(10);
DECLARE the_currency varchar(10);

WHILE counter <= (select count(distinct ticker) from currency c) DO
	call targetArray;

	set the_base = (select base from currency where id = 24);

	set the_currency = (select ticker from TArray where ID = counter);

	SET @DynamicSQL = 'CREATE TABLE ' + the_currency +
	' AS (select ticker, base, time, value from currency c
	where ticker = '+ the_currency + ' and base = ' + the_base +');';

	prepare stmt from @DynamicSQL;
	EXECUTE stmt;
	DEALLOCATE PREPARE stmt;
	SET counter = counter + 1;
END WHILE;
END $$
Delimiter ;

Drop procedure if exists buildTargetTables;

CALL buildTargetTables(); ;