CREATE DEFINER=`root`@`localhost` PROCEDURE `Exchange`.`findArbitrage`()
BEGIN
	
DROP TABLE IF EXISTS Triangle;
	
CREATE TABLE Triangle (
	LegOne varchar(10),
	LegTwo varchar(10),
	LegThree varchar(10),
	LegOneValue Decimal(16,8),
	LegTwoValue Decimal(16,8),
	LegThreeValue Decimal(16,8),
	LegTotal Decimal(16,8),
	Delta Decimal(16,8),
	FullCombo varchar(15)
	);
		
	

set @mainCounterOne = 1;

set @mainCounterTwo = 1;

set @mainCounterThree = 1;

CREATE TABLE firstList AS
SELECT DISTINCT base FROM currency;

ALTER TABLE	firstList 
ADD COLUMN ID INT AUTO_INCREMENT PRIMARY KEY FIRST;

CREATE TABLE secondList AS
SELECT * FROM firstList;

CREATE TABLE thirdList AS
SELECT * FROM firstList;



loop_one: LOOP
IF @mainCounterOne > (SELECT COUNT(*) FROM firstList) THEN
LEAVE loop_one;
	END IF;
	set @the_base = (select base from firstList where ID = @mainCounterOne);
	
	set @mainCounterOne = @mainCounterOne + 1;


		

	loop_two: LOOP
	IF @mainCounterTwo > (SELECT COUNT(*) FROM secondList) THEN
	LEAVE loop_two;
	END IF;

		IF (select base from secondList where ID = @mainCounterTwo) != @the_base THEN
			set @the_ticker = (select base from secondList where ID = @mainCounterTwo);
			set @mainCounterTwo = @mainCounterTwo + 1;
		ELSE
			set @mainCounterTwo = @mainCounterTwo + 1;
			ITERATE loop_two;
			
		END IF;
	
		
		
	
		
		loop_three: LOOP
		IF @mainCounterThree > (SELECT COUNT(*) FROM thirdList) THEN
		LEAVE loop_three;
		END IF;
	
		IF (select base from thirdList where ID = @mainCounterThree) = @the_base THEN
			set @mainCounterThree = @mainCounterThree + 1;
			ITERATE loop_three;
		
		ELSEIF (select base from thirdList where ID = @mainCounterThree) = @the_ticker THEN
			set @mainCounterThree = @mainCounterThree + 1;
			ITERATE loop_three;
		ELSE
			set @the_closer = (select base from thirdList where ID = @mainCounterThree);
			set @mainCounterThree = @mainCounterThree + 1;
		
		END IF;
			
		
			set @comboOne = CONCAT(@the_ticker,'_',@the_base);
			set @comboTwo = CONCAT(@the_closer,'_',@the_ticker);
			set @comboThree = CONCAT(@the_base,'_',@the_closer);
		
			set @leg_one_value = (select value from currency 
			where ticker = @the_ticker   AND base = @the_base);
		
			set @leg_two_value = (select value from currency 
			where ticker = @the_closer AND base = @the_ticker);
		
			set @leg_three_value = (select value from currency 
			where ticker = @the_base AND base = @the_closer);
		
			set @TempTotal = (@leg_one_value * @leg_two_value * @leg_three_value );
		
			set @legTotal = CONVERT(@TempTotal, DECIMAL (16,8));
		
			set @Delta = ABS(@legTotal - 1.00000000);
		
			set @FullCombo =  CONCAT(@the_base,'_',@the_ticker,'_',@the_closer);
		
			INSERT INTO Triangle (LegOne, LegTwo, LegThree, LegOneValue, LegTwoValue, LegThreeValue, LegTotal, Delta, FullCombo) values 
			(@comboOne, @comboTwo, @comboThree, @leg_one_value, @leg_two_value, @leg_three_value, @LegTotal, @Delta, @FullCombo ); 
			
			
			
		
		
		END LOOP;
		set @mainCounterThree = 1; 
	
	END LOOP;
	set @mainCounterTwo = 1;

END LOOP;

-- ALTER TABLE	Triangle 
-- MODIFY LegOneValue REAL, 
-- MODIFY LegTwoValue REAL, 
-- MODIFY LegThreeValue REAL;

-- ALTER TABLE	Triangle 
-- ADD COLUMN ArbitrageOpportuity DOUBLE GENERATED ALWAYS AS (LegOneValue * LegTwoValue * LegThreeValue) STORED;

-- ALTER TABLE	Triangle 
-- MODIFY COLUMN ArbitrageOpportunity DECIMAL(2,1);


DELETE FROM Triangle WHERE legTotal is null;
DROP TABLE firstList, secondList, thirdList; 			
END