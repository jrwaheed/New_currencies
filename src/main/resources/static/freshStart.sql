CREATE DEFINER=`root`@`localhost` PROCEDURE `Exchange`.`FreshStart`()
BEGIN

set @counter = 1;

set @entryCounter = (SELECT COUNT(*) FROM Exchange.currency);

loop_label: LOOP
IF @entryCounter < @counter THEN
	LEAVE loop_label;
	END IF;
	
	set @currentTable = (select combo from currency where id = @counter);
	
	Set @deleteTableString = CONCAT('DROP TABLE ',@currentTable);
	
	prepare statementOne FROM @deleteTableString;
	
	execute statementOne;
	
	set @counter = @counter + 1;

END LOOP;

DROP TABLE Triangle;
TRUNCATE TABLE currency; 


END