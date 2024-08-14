package com.keons.GameList.Infrastructure.Repositories;

import com.keons.Core.Application.Repositories.APIRepository;
import com.keons.GameList.Application.Repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.http.HttpRequest;
import java.util.Map;

@Repository
public class GameListRepositoryImpl extends APIRepository implements GameListRepository {

    @Value("${api.rawg.endpoint}")
    private String endPoint;

    @Value("${api.rawg.key}")
    private String apiKey;

    @Override
    public String getEndpoint() {
        return endPoint;
    }

    public Map<String, Object> findAll() {
        try{
            HttpRequest request = buildGetRequest(generateURI("games"));
            String response = sendRequest(request).body();
            return transformResponse(response);
        } catch (Exception exception){
            throw new RuntimeException("Error al obtener datos:" + exception.getMessage());
        }
    }

    public Map<String, Object> findById(long id) {
        try {
            HttpRequest request = buildGetRequest(generateURI("games/" + id));
            String response = sendRequest(request).body();
            return transformResponse(response);
        } catch (Exception exception){
            throw new RuntimeException("Error al obtener datos:" + exception.getMessage());
        }
    }

    private String generateURI(String pathParameter){
        return getEndpoint() + "/" + pathParameter + "?key=" + apiKey;
    }


}
