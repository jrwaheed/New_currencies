USE APICurrencies;

set @numberOfCurrencies =
(select count(DISTINCT target_currency) 
from currency_item_mapping);

SELECT @numberOfCurrencies;


while @numberOfCurrencies > 0
Begin 
	IF NOT EXISTS
	CREATE table 
	select distinct target_currency
	from currency_item_mapping
	where ROW_NUMBER() = @numberOfCurrencies
	
END

select distinct target_currency
from currency_item_mapping
where ROW_NUMBER() = 3;






