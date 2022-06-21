package nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.certificate;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanCategory;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.AbstractApiScan;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CertificateAbstractApiScan extends AbstractApiScan {
    private final String baseURL = "https://tls-observatory.services.mozilla.com/api/v1";
    private int scanId;

    @Override
    public void execute(ScanCategory scanCategory, String website) throws IOException, InterruptedException {
        super.execute(scanCategory, website);

        startScan();
        do {
            scanId = getScanId();
            TimeUnit.SECONDS.sleep(1);
        } while (!isFinished());
    }

    private void startScan() throws IOException, InterruptedException {
        String endpoint = "/analyze";
        String url = baseURL + endpoint + "?host=" + getWebsite();

        Map<String, String> body = new HashMap<>();
        body.put("rescan", "true");
        body.put("hidden", "true");

        String requestBody = new ObjectMapper()
                .writeValueAsString(body);

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(url)
                )
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        http.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public int getScanId() throws IOException, InterruptedException {
        String endpoint = "/analyze";
        String url = baseURL + endpoint + "?host=" + getWebsite();

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(url)
                )
                .GET()
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(new JSONObject(response.body()));
        setResult(new JSONObject(response.body()));

        return new JSONObject(response.body()).getInt("scan_id");
    }

    @Override
    public JSONObject getResult() throws IOException, InterruptedException {
        String endpoint = "/getScanResults";
        String url = baseURL + endpoint + "?scan=" + scanId;

        HttpClient http = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create(url)
                )
                .GET()
                .build();

        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        setResult(new JSONObject(response.body()));

        return super.getResult();
    }

    private boolean isFinished() throws IOException, InterruptedException {
        return super.getResult().get("state").equals("FINISHED");
    }

}
