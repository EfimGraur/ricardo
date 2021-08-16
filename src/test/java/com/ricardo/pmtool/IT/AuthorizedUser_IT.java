package com.ricardo.pmtool.IT;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static com.ricardo.pmtool.constants.RequestMappings.*;
import static com.ricardo.pmtool.testdata.TestData.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(
        scripts = "/db/it_data.sql"
)
@Sql(
        scripts = "/db/it_cleanup_data.sql",
        executionPhase = AFTER_TEST_METHOD
)
public class AuthorizedUser_IT {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    private HttpEntity authorizedRequest;

    @Before()
    public void setup() throws JSONException, JsonProcessingException {
        var loginObj = new JSONObject().put("email", TEST_LOGIN_EMAIL).put("password", TEST_PASSWORD);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> loginRequest =
                new HttpEntity<>(loginObj.toString(), headers);

        ResponseEntity<String> response = restTemplate.postForEntity(TEST_BASE_URL + port + "/" + LOGIN_URL, loginRequest, String.class);

        JsonNode tokenValue = new ObjectMapper().readTree(response.getBody()).get("token");
        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.set("Authorization", tokenValue.textValue());
        authHeaders.setContentType(MediaType.APPLICATION_JSON);

        authorizedRequest = new HttpEntity<>(authHeaders);
    }

    @AfterAll
    public static void teardown() {
    }

    @Test
    public void getAllUsersAsAdminSuccess() throws JSONException {
        String expected =
                "[{\"id\":1,\"email\":\"admin@mail.com\",\"firstName\":\"admin\",\"lastName\":\"adminov\",\"username\":\"superadmin\",\"role\":\"ADMIN\",\"password\":null}," +
                        "{\"id\":3,\"email\":\"pm@mail.com\",\"firstName\":\"Bill\",\"lastName\":\"Clinton\",\"username\":\"billondop\",\"role\":\"PM\",\"password\":null}," +
                        "{\"id\":5,\"email\":\"pm2@mail.com\",\"firstName\":\"Frantz\",\"lastName\":\"Ferdinand\",\"username\":\"bigfrantz\",\"role\":\"PM\",\"password\":null}," +
                        "{\"id\":6,\"email\":\"pm3@mail.com\",\"firstName\":\"Monica\",\"lastName\":\"Beltran\",\"username\":\"mariamira\",\"role\":\"PM\",\"password\":null}," +
                        "{\"id\":7,\"email\":\"pm4@mail.com\",\"firstName\":\"Francesca\",\"lastName\":\"Stravoni\",\"username\":\"francesca\",\"role\":\"DEV\",\"password\":null}]";
        ResponseEntity<String> response = restTemplate.exchange(
                USERS_URL, HttpMethod.GET, authorizedRequest, String.class);
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    @Test
    public void getAllProjectsAsAdminSuccess() throws JSONException {
        String expected =
                "[{\"id\":1,\"code\":\"ABCD1\",\"name\":\"PharmaCom\",\"assignee\":\"billondop\"}," +
                        "{\"id\":3,\"code\":\"IUHU2\",\"name\":\"Tracom\",\"assignee\":\"billondop\"}," +
                        "{\"id\":5,\"code\":\"NUKA4\",\"name\":\"Facephone\",\"assignee\":\"francesca\"}," +
                        "{\"id\":6,\"code\":\"IGQQ3\",\"name\":\"BlueDog\",\"assignee\":\"francesca\"}," +
                        "{\"id\":7,\"code\":\"VISA2\",\"name\":\"WatchCat\",\"assignee\":\"francesca\"}]";
        ResponseEntity<String> response = restTemplate.exchange(
                PROJECTS_URL, HttpMethod.GET, authorizedRequest, String.class);
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    @Test
    public void getAllTasksAsAdminSuccess() throws JSONException {
        String expected =
                "[{\"id\":1,\"description\":\"hard task\",\"pm\":\"billondop\",\"progress\":33,\"status\":\"NEW\",\"deadline\":null,\"projectCode\":\"IUHU2\",\"assignee\":\"francesca\"}," +
                        "{\"id\":2,\"description\":\"very hard task\",\"pm\":\"billondop\",\"progress\":44,\"status\":\"NEW\",\"deadline\":null,\"projectCode\":\"IUHU2\",\"assignee\":\"francesca\"}," +
                        "{\"id\":3,\"description\":\"ultra hard task\",\"pm\":\"billondop\",\"progress\":55,\"status\":\"NEW\",\"deadline\":null,\"projectCode\":\"IUHU2\",\"assignee\":\"billondop\"}]";

        ResponseEntity<String> response = restTemplate.exchange(
                TASKS_URL, HttpMethod.GET, authorizedRequest, String.class);
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }
}
