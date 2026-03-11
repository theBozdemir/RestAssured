package API_Recap.WeatherAPIFromLocal;

public class Weather {
    private double temp_c;
    private double humidity;
    private String condition;
    private double wind_speed_kph;

    public double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(double temp_c) {
        this.temp_c = temp_c;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getWind_speed_kph() {
        return wind_speed_kph;
    }

    public void setWind_speed_kph(double wind_speed_kph) {
        this.wind_speed_kph = wind_speed_kph;
    }
}
