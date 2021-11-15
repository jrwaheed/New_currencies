Delimiter $$

CREATE PROCEDURE buildTargetTables()
BEGIN

	set @counter = 1;

WHILE @counter <= (select count(distinct ticker) from currency) DO

	call targetArray;

	set @the_base = (select base from currency where id = 24);

	set @the_currency = (select ticker from TArray where ID = @counter);

	SET @DynamicDrop = CONCAT('DROP TABLE IF EXISTS ' ,@the_currency, ';');

	SET @DynamicSQL = CONCAT('CREATE TABLE ', @the_currency,'
	AS (select * from currency where ticker = ',"@the_currency",' AND base = ',"@the_base",');');

	SELECT @DynamicSQL;

	prepare dropstmt from @DynamicDrop;

	prepare stmt from @DynamicSQL;

	EXECUTE dropstmt;

	DEALLOCATE PREPARE dropstmt;

	EXECUTE stmt;

	DEALLOCATE PREPARE stmt;

	SET @counter = @counter + 1;

END WHILE;
END$$

Delimiter ;