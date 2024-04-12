package com.geolocate;

import com.geolocate.domain.model.Location;
import com.geolocate.domain.repository.LocationRepository;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressApiTest {

    @LocalServerPort
    Integer port;

    @Autowired
    WebTestClient webTestClient;

    @MockBean
    private LocationRepository locationRepository;

    @Value("${application.path.geolocatesvc}")
    private String pathGeolocateSvc;

    @Value("${application.security.username}")
    private String securityUsername;

    @Value("${application.security.password}")
    private String securitYPassword;

    @Value("${application.googleApiKey}")
    private String googleApiKey;
    private WireMockServer wireMockServer;

    @BeforeEach
    public void init() {
        WireMock.configureFor("localhost", 9000);
        wireMockServer = new WireMockServer(9000);
        wireMockServer.start();

        when(locationRepository.save(ArgumentMatchers.any(Location.class))).thenReturn(new Location());
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void addressLocatation_Response() throws IOException {
        String latitude = "-26.1965843";
        String longitude = "-52.6890572";

        mockingGoogleApi(latitude, longitude);

        webTestClient.get()
                .uri("http://localhost:" + port + pathGeolocateSvc +  "?latitude=" + latitude + "&longitude=" + longitude)
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString(
                        (securityUsername+":"+securitYPassword).getBytes()))
                .exchange().expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().json("{\"address\":\"Av. Elisa Rosa Cola Padoan, 333-343 - Fraron, Pato Branco - PR, Brasil\"}",true);

    }

    @NotNull
    private static String getFileContent(String location) throws IOException {
        return new String(Objects.requireNonNull(AddressApiTest.class.getClassLoader()
                .getResourceAsStream(location)).readAllBytes());
    }

    private void mockingGoogleApi(String latitude, String longitude) throws IOException {
        stubFor(
                get(urlEqualTo("/maps/api/geocode/json?key="+googleApiKey+"&latlng="+latitude+","+longitude))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(getFileContent("wiremock/__files/google_raw.json"))));
    }
}
