package com.example.springback.clasesolas;

import java.io.IOException;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PruebaJsoup {

	public PruebaJsoup() {

	}

	public static int getStatusConnectionCode(String url) {

		Response response = null;

		try {
			response = Jsoup.connect(url).userAgent("Chrome/41.0.2228.0").timeout(100000).ignoreHttpErrors(true)
					.execute();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
		}
		return response.statusCode();
	}

	
	private static Document getHtmlDocument(String url) {

	    Document doc = null;
		try {
		    doc = Jsoup.connect(url).userAgent("\"Chrome/41.0.2228.0\"").timeout(100000).get();
		    } catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		    }
	    return doc;
	}
	
	public void extraW(String url) {
		
		String code = "null";
		try {
			 code = getStatusConnectionCode(url) + "";
			 	if(code.equals("200")) {
			 		//OBTENGO LA WEB DEL DOCUMENTO
					Document documents = getHtmlDocument(url);
					
					// Busco todas las entradas que estan dentro de: 
			        Elements entradas = documents.select("div.col-md-4.col-xs-12").not("div.col-md-offset-2.col-md-4.col-xs-12");
			        //System.out.println("Número de entradas en la página inicial de Jarroba: "+entradas.size()+"\n");
			        
			        for (Element elem : entradas) {
			            String titulo = elem.getElementsByClass("tituloPost").text();
			            String autor = elem.getElementsByClass("autor").toString();
			            String fecha = elem.getElementsByClass("fecha").text();
						
			            System.out.println(titulo+"\n"+autor+"\n"+fecha+"\n\n");
						
			            // Con el método "text()" obtengo el contenido que hay dentro de las etiquetas HTML
			            // Con el método "toString()" obtengo todo el HTML con etiquetas incluidas
			        }
						
			 	}else {
			 		System.out.println("Url no Habilitado");
			 	}
			 
		} catch (Exception e) {
			System.out.println(code + e);
		}

	} 
}
