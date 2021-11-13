select count(distinct ticker)
from currency c ;

select distinct base
from currency c ;






-- Array of Targets, procedure
Delimiter //

CREATE PROCEDURE targetArray()
BEGIN	
	DROP TABLE IF EXISTS TArray;		
	CREATE TABLE TArray AS (select distinct ticker from currency c );
	ALTER TABLE TArray add ID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY;
END //

delimiter ;

call targetArray;

select * from TArray;


-- Count the number of separate target currencies
set @number_of_targets = 
(select count(distinct ticker)
	from currency c);

select @number_of_targets;




-- Create a new table from a query
CREATE TABLE JPY
	AS (select ticker, base, time, value from currency c 
where ticker = 'JPY'
and base = 'USD');




-- THE LOOP


Delimiter $$

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
	
		declare @DynamicSQL nvarchar(max)
	
		set @DynamicSQL = N'CREATE TABLE ' +@theCurrency+ 
		' AS (select ticker, base, time, value from currency c 
				where ticker = '+@theCurrency+ ' and base = ' +@theBase+');'
			
				
		EXEC (@DynamicSQL);
	
		CREATE TABLE testMonkey
			AS (select ticker, base, time, value from currency c 
				where ticker = @theCurrency and base = @theBase);
	END WHILE;

END $$

Delimiter;








DROP PROCEDURE LoopDemo;

DELIMITER $$
CREATE PROCEDURE LoopDemo()
BEGIN
	DECLARE x  INT;
	DECLARE str  VARCHAR(255);
        
	SET x = 1;
	SET str =  '';
        
	loop_label:  LOOP
		IF  x > 10 THEN 
			LEAVE  loop_label;
		END  IF;
            
		SET  x = x + 1;
		IF  (x mod 2) THEN
			ITERATE  loop_label;
		ELSE
			SET  str = CONCAT(str,x,',');
		END  IF;
	END LOOP;
	SELECT str;
END$$

DELIMITER ;

