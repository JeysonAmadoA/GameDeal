package com.keons.Core.Application.Repositories;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public abstract class APIRepository {

    protected abstract String getEndpoint();

    protected HttpClient getNewClient(){
        return HttpClient.newHttpClient();
    }

    protected HttpRequest buildGetRequest(String url){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    protected HttpRequest buildPostRequest(String url, HttpRequest.BodyPublisher bodyPublisher){
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(bodyPublisher)
                .build();
    }

    protected HttpResponse<String> sendRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpClient client = getNewClient();
                return client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
    }

    protected Map<String, Object> transformResponse(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, new TypeReference<>() {});
        } catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }

}
