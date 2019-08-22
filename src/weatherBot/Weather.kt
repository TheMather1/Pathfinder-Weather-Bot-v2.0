package weatherBot

import weatherBot.location.Location
import weatherBot.precipitation.Precipitation
import weatherBot.time.Season

class Weather {
    val temp: Int;
    val precipitation: Precipitation?;
    val clouds: Clouds;
    constructor(location: Location, season: Season){
        var newTemp = season.temp(location)
        precipitation = Precipitation(location, season, newTemp)
        clouds = if (precipitation == null) Clouds() else Clouds.OVERCAST.also { newTemp += it.adjustTemp(season) }
        temp = newTemp
    }
}