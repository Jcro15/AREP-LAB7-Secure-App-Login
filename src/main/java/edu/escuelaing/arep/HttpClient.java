package edu.escuelaing.arep;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClient {
    /**
     * Sobreescribe la configuración del trustedstore para confiar solo en el servidor de logica de aws, del que se tiene
     * certificado en la carepta keystores, esto permite realizar peticiones a este servicio por un canal seguro
     */
    public static void init() {
        // Create a file and a password representation
        File trustStoreFile = new File("keystores/myTrustStore");
        char[] trustStorePassword = "567890".toCharArray();
        // Load the trust store, the default type is "pkcs12", the alternative is "jks"
        KeyStore trustStore = null;
        TrustManagerFactory tmf = null;
        SSLContext sslContext = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);
            // Get the singleton instance of the TrustManagerFactory
            tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            // Itit the TrustManagerFactory using the truststore object
            tmf.init(trustStore);
            //Set the default global SSLContext so all the connections will use it
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
        } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realizar la peticion get al servidor de logica en aws
     * @return la respuesta del servicio
     * @throws IOException si no se ecuentra las respuesta
     */
    public static String getService() throws IOException {
        URL url = new URL("https://ec2-35-170-245-94.compute-1.amazonaws.com:8081/service");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();

        }
    }

}
