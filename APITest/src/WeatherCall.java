public class WeatherCall {
  public static void main(String[] args) {
    String apiURL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?";
    apiURL += "serviceKey=서비스키";
    apiURL += "&numOfRows=50";
    apiURL += "&pageNo=1";
    apiURL += "&dataType=JSON";
    apiURL += "&base_date=20250812";
    apiURL += "&base_time=0800";
    apiURL += "&nx=98";
    apiURL += "&ny=76";

    

  }
}