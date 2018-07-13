package com.test.resource;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class LocalizacaoAPI {

	private final String uriLocale = "https://ipvigilante.com";
	private final String uriClima = "https://www.metaweather.com/api/location/";
	private RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping
	public void returnInformation() throws JSONException, ParseException {
		
		String ip = retornarIp();
		String nomeCidade = retornarCidade();
		String latlong = retornarLat() + "," + retornarLon();
		System.out.println(latlong);
		String tempMin = retornarTempMin(latlong);
		String tempMax = retornarTempMax(latlong);
		
		System.out.println("ip:" + ip);
		System.out.println("Nome da Cidade:" + nomeCidade);
		System.out.println("Temperatura Mínima:" + tempMin);
		System.out.println("Temperatura Máxima:" + tempMax);
	}
	
	public String retornarIp() {
		String ip = "";
		
		JSONObject resultado = restTemplate.getForObject(uriLocale, JSONObject.class);
		
		String[] resultadosString = resultado.get("data").toString().split(",|\\}|\\{");
		
		for (int i = 0; i < resultadosString.length; i ++) {
			if (resultadosString[i].contains("ipv4")) {
				String ipv4Results[] = resultadosString[i].split("=");
				ip = ipv4Results[1];
			}
		}
		
		return ip;
	}
	
	public String retornarCidade() {
		String nomeCidade = "";
		
		JSONObject resultado = restTemplate.getForObject(uriLocale, JSONObject.class);
		
		String[] resultadosString = resultado.get("data").toString().split(",");
		
		for (int i = 0; i < resultadosString.length; i ++) {
			if (resultadosString[i].contains("city_name")) {
				String resultadosCidade[] = resultadosString[i].split("=");
				nomeCidade = resultadosCidade[1];
			}
		}
		
		return nomeCidade;
	}
	
	public String retornarLat() {
		String lat = "";
		
		JSONObject resultado = restTemplate.getForObject(uriLocale, JSONObject.class);
		
		String[] resultadosString = resultado.get("data").toString().split(",|\\}|\\{");
		
		for (int i = 0; i < resultadosString.length; i ++) {
			if (resultadosString[i].contains("latitude")) {
				String resultadosLat[] = resultadosString[i].split("=");
				lat = resultadosLat[1];
			}
		}
		
		return lat;
	}
	
	public String retornarLon() {
		String lon = "";
		
		JSONObject resultado = restTemplate.getForObject(uriLocale, JSONObject.class);
		
		String[] resultadosString = resultado.get("data").toString().split(",|\\}|\\{");
		
		for (int i = 0; i < resultadosString.length; i ++) {
			if (resultadosString[i].contains("longitude")) {
				String resultadosLon[] = resultadosString[i].split("=");
				lon = resultadosLon[1];
			}
		}
		
		return lon;
	}
	
	public String getWoeID(String latlong) {
		String buscarCidade = uriClima + "search/?lattlong=" + latlong;
		String id = "";
		
		
		String resultado = restTemplate.getForObject(buscarCidade, String.class);
					
		String divisoes[] = resultado.split(",");
		
		for (int i = 0;i < divisoes.length; i ++) {
			if (divisoes[i].contains("woeid") && id.isEmpty()) {
				String woeid[] = divisoes[i].split(":");
				id = woeid[1];
			}
		}
		
		return id;
	}
	
	public String retornarTempMax(String latlong) throws JSONException {
		String buscarMax = uriClima + getWoeID(latlong);
		String tempMax = "";

		String resultado = restTemplate.getForObject(buscarMax, String.class);
		String divisoes[] = resultado.split(",");
		for (int i = 0;i < divisoes.length; i ++) {
			if (divisoes[i].contains("max_temp") && tempMax.isEmpty()) {
				String maxTemp[] = divisoes[i].split(":|\\.");
				tempMax = maxTemp[1];
			} 
		}
		
		return tempMax + " °C";
	}
	
	public String retornarTempMin(String latlong) throws JSONException {
		String buscarMin = uriClima + getWoeID(latlong);
		String tempMin = "";

		String resultado = restTemplate.getForObject(buscarMin, String.class);
		String divisoes[] = resultado.split(",");
		for (int i = 0;i < divisoes.length; i ++) {
			if (divisoes[i].contains("min_temp") && tempMin.isEmpty()) {
				String minTemp[] = divisoes[i].split(":|\\.");
				tempMin = minTemp[1];
			}
		}
		
		return tempMin + " °C";
	}
}
