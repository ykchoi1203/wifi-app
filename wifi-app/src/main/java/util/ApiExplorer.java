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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ApiExplorer {
//	private HttpURLConnection conn;
	
	public ApiExplorer() {}
	
	public List<WifiDto> getWifi() throws IOException {
		StringBuilder urlBuilder = urlBuilder(1, 10);
			
		List<WifiDto> list = new ArrayList<>();;
	
		long totalCount = 0;
				
		String data = getWifiJson(urlBuilder);
		
		try {
			JSONParser jsonParser = new JSONParser();
			//JSON데이터를 넣어 JSON Object 로 만들어 준다.
			JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
			//books의 배열을 추출
			JSONObject jObj = (JSONObject) jsonObject.get("TbPublicWifiInfo");

			totalCount = (long)jObj.get("list_total_count");
			int start = 1;
			int end = (int)totalCount >= 1000 ? 1000 : (int)totalCount;
			
			while(start < totalCount) {
				urlBuilder = urlBuilder(start, end);
				data = getWifiJson(urlBuilder);
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
	
	public String getWifiJson(StringBuilder urlBuilder) throws IOException {
		
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(urlBuilder.toString()).build();

		  try (Response response = client.newCall(request).execute()) {
		    return response.body().string();
		}

	}
}
