package com.project.springbootgraphql;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.project.springbootgraphql.models.Cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteGraphQLTest extends GraphQLTestTemplate {

    @Test
    public void testClientes() throws IOException, JSONException {

        GraphQLResponse response = perform("cliente.graphql", null);

        assertTrue(response.isOk());

        String json = response.getRawResponse().getBody();
        System.out.println("Body: " + json);

        JSONArray jSONArray = new JSONObject(json).getJSONObject("data").getJSONArray("clientes");
        System.out.println(jSONArray);

        ObjectMapper mapper = new ObjectMapper();

        List<Cliente> clientes = mapper.readValue(jSONArray.toString(), new TypeReference<List<Cliente>>() {
        });

        clientes.forEach(c -> System.out.println(c.getNome()));
    }
}