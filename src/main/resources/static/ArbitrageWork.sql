DELIMITER //

CREATE PROCEDURE findArbitrage()
BEGIN
	
CREATE TABLE Triangle (
	LegOne varchar(10),
	LegTwo varchar(10),
	LegThree varchar(10),
	LegOneValue double,
	LegTwoValue double,
	LegThreeValue double
	);
		
	
set @mainCounterOne = 1;

set @mainCounterTwo = 2;

set @mainCounterThree = 3;

CREATE TABLE firstList AS
SELECT DISTINCT base FROM currency;

ALTER TABLE	firstList 
ADD COLUMN ID INT AUTO_INCREMENT PRIMARY KEY FIRST;

set @firstListCounter = (SELECT COUNT(*) FROM firstList) ;



loop_one: LOOP
IF (SELECT COUNT(*) FROM firstList) = 0 THEN
	LEAVE loop_one;
	END IF;

	DELETE FROM secondList WHERE base = @the_base;

	CREATE TABLE secondList AS
	SELECT * FROM firstList;

	set @the_base = (select base from firstList where ID = @mainCounterOne);

	set @mainCounterOne = @mainCounterOne + 1;

	DELETE FROM firstList WHERE base = @the_base;
	DELETE FROM secondList WHERE base = @the_base;
	
	



loop_two: LOOP
	IF (SELECT COUNT(*) FROM secondList) = 0 THEN
	LEAVE loop_two;
	END IF;

		CREATE TABLE thirdList AS
		SELECT * FROM firstList;
	


		set @the_ticker = (select base from secondList where ID = @mainCounterTwo);
	
		set @comboOne = CONCAT(@the_ticker,'_',@the_base);

		set @leg_one_value = (select value from currency 
		where base = @the_base AND ticker = @the_ticker);
			
		set @mainCounterTwo = @mainCounterTwo + 1;
	
		
		DELETE FROM thirdList WHERE base = @the_base;
		DELETE FROM thirdList WHERE base = @the_ticker;
	
	
	
		loop_three: LOOP
		IF (SELECT COUNT(*) FROM thirdList) = 0 THEN
		LEAVE loop_three;
		END IF;
	
			
			
			set @the_closer = (select base from thirdList where ID = @mainCounterThree);
		
			SELECT @the_closer;
		
			set@comboTwo = CONCAT(@the_closer,'_',@the_ticker);
			set@comboThree = CONCAT(@the_base,'_',@the_closer);
		
			set @leg_two_value = (select value from currency 
			where ticker = @the_closer AND base = @the_ticker);
		
			set @leg_three_value = (select value from currency 
			where ticker = @the_base AND base = @the_closer);
		
			INSERT INTO Triangle (LegOne, LegTwo, LegThree, LegOneValue, LegTwoValue, LegThreeValue) values 
			(@comboOne, @comboTwo, @comboThree, @leg_one_value, @leg_two_value, @leg_three_value); 
		
			set @mainCounterThree = @mainCounterThree + 1;
		
			DELETE FROM thirdList WHERE base = @the_closer;
		
		END LOOP;
	END LOOP;
END LOOP;


			
END //

DELIMITER ;



