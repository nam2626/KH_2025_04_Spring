package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeminiExample1 {
  public static void main(String[] args) throws IOException {
    String apiURL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";

    URL url = new URL(apiURL);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoOutput(true);
    conn.setRequestProperty("x-goog-api-key","서비스키");
    conn.setRequestProperty("Content-Type","application/json");

    String data = "{\"contents\": [{\"parts\": [{\"text\": \"마이바티스가 뭔지 설명좀 해줘.\"}]}]}";
    //요청 데이터 전송
    conn.getOutputStream().write(data.getBytes());
    conn.getOutputStream().flush();

    //데이터 결과 받기
    try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
      String result = "";
      String temp = "";
      while((temp = br.readLine()) != null)
        result += temp;

      System.out.println(result);

    }catch (Exception e){
      e.printStackTrace();
    }
    conn.disconnect();
  }
}
