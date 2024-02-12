package main.java.util;

import java.io.IOException;
// import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

import main.java.dto.WifiDto;
import okhttp3.*;


public class ApiExplorer {
//	private HttpURLConnection conn;
	
	public ApiExplorer() {}
	
	public List<WifiDto> getWifi() throws IOException {
		ConnectionPool connectionPool = new ConnectionPool();
		StringBuilder urlBuilder = urlBuilder(1, 10);
			
		List<WifiDto> list = new ArrayList<>();;
	
		long totalCount = 0;
				
		String data = getWifiJson(urlBuilder, connectionPool);
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
			JSONObject jObj = (JSONObject) jsonObject.get("TbPublicWifiInfo");

			totalCount = (long)jObj.get("list_total_count");
			int start = 1;
			int end = (int)totalCount >= 1000 ? 1000 : (int)totalCount;
			
			while(start < totalCount) {
				urlBuilder = urlBuilder(start, end);
				data = getWifiJson(urlBuilder, connectionPool);
				jsonObject = (JSONObject) jsonParser.parse(data);

				jObj = (JSONObject) jsonObject.get("TbPublicWifiInfo");

				JSONArray rowArray = (JSONArray) jObj.get("row");
				Gson gson = new Gson();

				for(int i=0; i<rowArray.size(); i++) {
					JSONObject rowObject = (JSONObject) rowArray.get(i);
					WifiDto wifi = gson.fromJson(rowObject.toString(), WifiDto.class);
					list.add(wifi);	
				}
				start = end+1;
				end = end + 1000 >= (int)totalCount ? (int)totalCount : end + 1000;
			}
	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return list;
		
	}
	
	public StringBuilder urlBuilder(int start, int end) throws IOException  {
		StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/614a4a6249796b63313033727a774d53/json/TbPublicWifiInfo"); /*URL*/
		urlBuilder.append("/" + start + "/" + end + "/"); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/

		return urlBuilder;
	}
	
	public String getWifiJson(StringBuilder urlBuilder, ConnectionPool connectionPool) throws IOException {
		
		OkHttpClient client = new OkHttpClient.Builder().connectionPool(connectionPool).build();
		Request request = new Request.Builder().url(urlBuilder.toString()).build();

		  try (Response response = client.newCall(request).execute()) {
		    return response.body().string();
		}

	}
}
