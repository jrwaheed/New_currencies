CREATE DEFINER=`root`@`localhost` PROCEDURE `Exchange`.`resetAutoIncrement`()
BEGIN
	UPDATE hibernate_sequence SET next_val = 1; 
END