package com.example.springback.util.exception;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

//@Slf4j
public class ResponseErrorHandlerImpl implements ResponseErrorHandler {
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {

		if (200 != response.getRawStatusCode() && 201 != response.getRawStatusCode()) {
			return true;
		}
		return false;
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {

		/*
		 * StringBuilder inputStringBuilder = new StringBuilder(); BufferedReader
		 * bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(),
		 * "UTF-8")); String line = bufferedReader.readLine();
		 * 
		 * while (line != null) { inputStringBuilder.append(line);
		 * inputStringBuilder.append('\n'); line = bufferedReader.readLine(); }
		 * 
		 * log.
		 * info("============================response begin=========================================="
		 * ); log.info("Status code  : {}", response.getStatusCode());
		 * log.info("Status text  : {}", response.getStatusText());
		 * log.info("Headers      : {}", response.getHeaders());
		 * log.info("Response body: {}", inputStringBuilder.toString()); log.
		 * info("=======================response end================================================="
		 * );
		 */

		throw new RuntimeException(response.getRawStatusCode() + " - " + response.getStatusCode().getReasonPhrase());

	}

}
