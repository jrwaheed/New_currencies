CREATE DEFINER=`root`@`localhost` PROCEDURE `Exchange`.`findMaxArbitrage`()
BEGIN

select FullCombo, Delta FROM Triangle where delta = (select MAX(delta) FROM Triangle); 

END