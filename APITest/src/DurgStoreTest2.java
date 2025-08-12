import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class DurgStoreTest2 {
  public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
    //건강보험심사평가원_약국정보서비스 데이터 요청해서 출력
    String apiURL = "http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList?";
    apiURL += "serviceKey=서비스키";
    apiURL += "&numOfRows=50";
    apiURL += "&pageNo=1";
    apiURL += "&emdongNm=" + URLEncoder.encode("양평동4가", StandardCharsets.UTF_8);

    //1. URL 객체 생성
    URL url = new URL(apiURL);
    //2. 접속 정보 생성
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    //3. 데이터 요청
    //4. 데이터를 받아서 출력
    System.out.println("ResponseCode : " + conn.getResponseCode());
    if (conn.getResponseCode() != 200)
      return;

      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newDefaultInstance();
      DocumentBuilder builder = builderFactory.newDocumentBuilder();
      Document document = builder.parse(conn.getInputStream());
      document.getDocumentElement().normalize();

      NodeList list = document.getElementsByTagName("item");

      for(int i=0;i<list.getLength();i++){
        NodeList childList = list.item(i).getChildNodes();
        for(int j=0;j<childList.getLength();j++){
          System.out.println(childList.item(j).getNodeName() + " / " + childList.item(j).getTextContent());
        }
      }




      conn.disconnect();
    }
  }

