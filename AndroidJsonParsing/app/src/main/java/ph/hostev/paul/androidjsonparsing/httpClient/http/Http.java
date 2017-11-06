package ph.hostev.paul.androidjsonparsing.httpClient.http;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
    private HttpURLConnection conn;
    private InputStream mIs = null;
    private static final String TAG = "Http";

    public synchronized void post(final String url, final String data, final ISuccess<String> pResponse) {
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.connect();

            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(data);
            writer.close();
            outputStream.close();


            int statusCode = conn.getResponseCode();

            if (statusCode >= 200 && statusCode < 400) {
                mIs = conn.getInputStream();
            } else {
                mIs = conn.getErrorStream();
            }

            final String response = readInputStream(mIs);

            if (pResponse != null) {
                pResponse.onSuccess(response);
            }

        } catch (IOException e) {
            Log.d(TAG, e.getMessage());

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (mIs != null) {
                try {
                    mIs.close();
                } catch (IOException e) {
                    Log.d(TAG, e.getMessage());

                }
            }


        }
    }

    public synchronized void get(final String url, final ISuccess<String> pResponse) {

        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            if (statusCode >= 200 && statusCode < 400) {
                mIs = conn.getInputStream();
            } else {
                mIs = conn.getErrorStream();
            }

            final String response = readInputStream(mIs);


            if (pResponse != null) {
                pResponse.onSuccess(response);

            }
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());

        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (mIs != null) {
                try {
                    mIs.close();
                } catch (IOException e) {
                    Log.d(TAG, e.getMessage());
                }


            }
        }


    }

    private String readInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }
}
