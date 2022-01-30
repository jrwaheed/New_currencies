fetch("https://cnbc.p.rapidapi.com/get-meta-data", {
	"method": "GET",
	"headers": {
		"x-rapidapi-host": "cnbc.p.rapidapi.com",
		"x-rapidapi-key": "SIGN-UP-FOR-KEY"
	}
})
.then(response => {
	console.log(response);
})
.catch(err => {
	console.error(err);
});