package com.Movie_Project.apiTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Movie_Project.dao.MemberDao;
import com.Movie_Project.dto.ReservationDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ApiService {
	
	@Autowired
	private MemberDao mdao;
	
	//카카오 토큰
	public String getAccessToken(String code) throws Exception{
		// 시작
		StringBuilder urlBuilder = new StringBuilder("https://kauth.kakao.com/oauth/token"); 
        urlBuilder.append("?" + URLEncoder.encode("grant_type","UTF-8") + "=" + URLEncoder.encode("authorization_code", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("client_id","UTF-8") + "=" + URLEncoder.encode("556f86cb11e1d4916481f261100dc55a", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("redirect_uri","UTF-8") + "=" + URLEncoder.encode("http://localhost:8080/controller/kakaoLoginTest", "UTF-8")); /*한 페이지 결과 수*/ 
        urlBuilder.append("&" + URLEncoder.encode("code","UTF-8") + "=" + URLEncoder.encode(code, "UTF-8")); 
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        
        JsonElement element = JsonParser.parseString(sb.toString());
        String access_token = element.getAsJsonObject().get("access_token").getAsString();
        System.out.println("access_token : " + access_token);
        
//        String refresh_token = element.getAsJsonObject().get("refresh_token").getAsString();
//        System.out.println("refresh_token : " + refresh_token);
		// 끝
		
		return access_token;
	}
	

	

	
	
	
	
	

	public String kakaoPay_ready(ReservationDto reserveInfo, HttpSession session) throws Exception {
		//1. 결제준비 : 결제고유번호(TID) 발급
		
		System.out.println("넘겨받은 "+reserveInfo);
		StringBuilder urlBuilder = new StringBuilder("https://kapi.kakao.com/v1/payment/ready"); 
        urlBuilder.append("?" + URLEncoder.encode("cid","UTF-8") + "=" + URLEncoder.encode("TC0ONETIME", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("partner_order_id","UTF-8") + "=" + URLEncoder.encode(reserveInfo.getRecode(), "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("partner_user_id","UTF-8") + "=" + URLEncoder.encode((String) session.getAttribute("loginId"), "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("item_name","UTF-8") + "=" + URLEncoder.encode("영화예매테스트", "UTF-8"));	/* 상품이름 */	
        urlBuilder.append("&" + URLEncoder.encode("quantity","UTF-8") + "=" + URLEncoder.encode(reserveInfo.getRecount()+"", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("total_amount","UTF-8") + "=" + URLEncoder.encode((reserveInfo.getRecount()*20000)+"", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("tax_free_amount","UTF-8") + "=" + URLEncoder.encode("0", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("approval_url","UTF-8") 
                          + "=" + URLEncoder.encode("http://localhost:8080/controller/reserveMovie_PayApproval?recode="+reserveInfo.getRecode(), "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("cancel_url","UTF-8") 
        				  + "=" + URLEncoder.encode("http://localhost:8080/controller/reserveMovie_PayCancel?recode="+reserveInfo.getRecode(), "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("fail_url","UTF-8") 
		  				  + "=" + URLEncoder.encode("http://localhost:8080/controller/reserveMovie_PayFail?recode="+reserveInfo.getRecode(), "UTF-8"));        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK "+"9e440f3ca8bfeb16e9ca4b77dad723cb");
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
		JsonElement readyElement = JsonParser.parseString(sb.toString());
		JsonObject readyObj = readyElement.getAsJsonObject();
		String tid = readyObj.get("tid").getAsString();
		System.out.println("tid : " + tid);
		session.setAttribute("payTid", tid);
		
		String nextPcUrl = readyObj.get("next_redirect_pc_url").getAsString();
		System.out.println(nextPcUrl);
		
		return nextPcUrl;        
      
	}


	public String kakaoPay_approval(String tid, String pg_token,String recode, HttpSession session) throws Exception {
		//1. 결제 승인 요청 
		StringBuilder urlBuilder = new StringBuilder("https://kapi.kakao.com/v1/payment/approve"); 
        urlBuilder.append("?" + URLEncoder.encode("cid","UTF-8") + "=" + URLEncoder.encode("TC0ONETIME", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("tid","UTF-8") + "=" + URLEncoder.encode(tid, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("partner_order_id","UTF-8") + "=" + URLEncoder.encode(recode, "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("partner_user_id","UTF-8") + "=" + URLEncoder.encode((String) session.getAttribute("loginId"), "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("pg_token","UTF-8") + "=" + URLEncoder.encode(pg_token, "UTF-8"));		
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "KakaoAK "+"9e440f3ca8bfeb16e9ca4b77dad723cb");
        conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        System.out.println("Response code: " + conn.getResponseCode());
        
        String result ="Fail";
        
        
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            result = "Approval";
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());		
		
        JsonElement approvalElement = JsonParser.parseString(sb.toString());
		JsonObject approvalObj = approvalElement.getAsJsonObject();
		String orcode = approvalObj.get("partner_order_id").getAsString();
		System.out.println("주문코드 : " + orcode);
		String prname = approvalObj.get("item_name").getAsString();
		System.out.println("상품이름 : " + prname);
		String prcount = approvalObj.get("quantity").getAsString();
        System.out.println("주문수량 : " + prcount);
        String totalprice = approvalObj.get("amount").getAsJsonObject().get("total").getAsString();
        System.out.println("주문총액 : " + totalprice);
		
        
		return result;
	}


	public ArrayList<BusTagoDto> getBusArrInfoList(String cityCode, String nodeId) throws Exception {
		System.out.println("버스 도착 정보 조회 서비스");
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=usANYAbDo6LE6esIxi93SNVGDVz%2Fjh0SAMBAr%2FXV80GEOY%2FNv3yeWe2mnyAnkAbkMZAIZ31yoyydOb61CX5d%2Bw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
        urlBuilder.append("&" + URLEncoder.encode("cityCode","UTF-8") + "=" + URLEncoder.encode(cityCode, "UTF-8")); /*도시코드 [상세기능3 도시코드 목록 조회]에서 조회 가능*/
        urlBuilder.append("&" + URLEncoder.encode("nodeId","UTF-8") + "=" + URLEncoder.encode(nodeId, "UTF-8")); /*정류소ID [국토교통부(TAGO)_버스정류소정보]에서 조회가능*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());		
		
        JsonElement element = JsonParser.parseString(sb.toString());
        JsonObject body = element.getAsJsonObject().get("response").getAsJsonObject().get("body").getAsJsonObject();
        JsonObject items = body.get("items").getAsJsonObject();
        JsonArray item = items.get("item").getAsJsonArray();
        //System.out.println(item.get(0).getAsJsonObject().get("nodenm").getAsString());
        
        ArrayList<BusTagoDto> arrInfoList = new ArrayList<BusTagoDto>();
        for(int i = 0; i < item.size(); i++) {
        	BusTagoDto arrInfo = new BusTagoDto();
        	
        	//버스 노선 번호
        	String routeno = item.get(i).getAsJsonObject().get("routeno").getAsString();
        	arrInfo.setRouteno(routeno);
        	System.out.print("버스번호(routeno) : " + routeno + ", ");
        	
        	//도착 예정 시간
        	int arrTime_sec = item.get(i).getAsJsonObject().get("arrtime").getAsInt();
        	String arrtime = ( arrTime_sec / 60 ) +"분 " + ( arrTime_sec % 60 ) + "초";
        	arrInfo.setArrtime(arrtime);
        	System.out.print("도착예정(arrtime) : " + arrtime+ ", ");
        	
        	//남은 정류장 수
        	String arrprevstationcnt = item.get(i).getAsJsonObject().get("arrprevstationcnt").getAsString();
        	arrInfo.setArrprevstationcnt(arrprevstationcnt);
        	System.out.println("남은 정류장 수 : " + arrprevstationcnt +"개");
        	
        	
        	arrInfoList.add(arrInfo);
        }
        
		return arrInfoList;
	}


	
	
	
	public String getArrInfoList(String cityCode, String nodeId) throws Exception {
		System.out.println("버스 도착 정보 조회 서비스");
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=usANYAbDo6LE6esIxi93SNVGDVz%2Fjh0SAMBAr%2FXV80GEOY%2FNv3yeWe2mnyAnkAbkMZAIZ31yoyydOb61CX5d%2Bw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
        urlBuilder.append("&" + URLEncoder.encode("cityCode","UTF-8") + "=" + URLEncoder.encode(cityCode, "UTF-8")); /*도시코드 [상세기능3 도시코드 목록 조회]에서 조회 가능*/
        urlBuilder.append("&" + URLEncoder.encode("nodeId","UTF-8") + "=" + URLEncoder.encode(nodeId, "UTF-8")); /*정류소ID [국토교통부(TAGO)_버스정류소정보]에서 조회가능*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());		
		
        JsonElement element = JsonParser.parseString(sb.toString());
        JsonObject body = element.getAsJsonObject().get("response").getAsJsonObject().get("body").getAsJsonObject();
        JsonObject items = body.get("items").getAsJsonObject();
        JsonArray item = items.get("item").getAsJsonArray();
        //System.out.println(item.get(0).getAsJsonObject().get("nodenm").getAsString());
        
        ArrayList<BusTagoDto> arrInfoList = new ArrayList<BusTagoDto>();
        for(int i = 0; i < item.size(); i++) {
        	BusTagoDto arrInfo = new BusTagoDto();
        	//버스 노선 번호
        	String routeno = item.get(i).getAsJsonObject().get("routeno").getAsString();
        	arrInfo.setRouteno(routeno);
        	System.out.print("버스번호(routeno) : " + routeno + ", ");
        	
        	//도착 예정 시간
        	int arrTime_sec = item.get(i).getAsJsonObject().get("arrtime").getAsInt();
        	String arrtime = ( arrTime_sec / 60 ) +"분 " + ( arrTime_sec % 60 ) + "초";
        	arrInfo.setArrtime(arrtime);
        	System.out.print("도착예정(arrtime) : " + arrtime+ ", ");
        	
        	//남은 정류장 수
        	String arrprevstationcnt = item.get(i).getAsJsonObject().get("arrprevstationcnt").getAsString();
        	arrInfo.setArrprevstationcnt(arrprevstationcnt);
        	System.out.println("남은 정류장 수 : " + arrprevstationcnt +"개");
        	arrInfoList.add(arrInfo);
        }
        Gson gson = new Gson();
        
        
		return gson.toJson(arrInfoList);
	}
	
	
	
	
	
	
	
	
}
