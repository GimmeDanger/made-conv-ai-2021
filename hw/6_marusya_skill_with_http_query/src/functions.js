function getCurrentWeather(city) {
	var apiKey = $jsapi.context().injector.weatherApiKey;
	var response = $http.get("http://api.openweathermap.org/data/2.5/weather?APPID=${APPID}&units=${units}&lang=${lang}&q=" + city, {
            timeout: 10000,
            query: {
                APPID: apiKey,
                units: "metric",
                lang: "ru",
            }
        });

	if (!response.isOk || !response.data) {
		return false;
	}
	
    log("@@@@@@@@@@ " + toPrettyString(response));
	
	var weather = {};
	weather.temp = response.data.main.temp;
	weather.description = response.data.weather[0].description;
	return weather;
}