package com.venu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

public class PrintRedirects {

    public static void main(String[] args) {

        try {

            String url = "https://secure3.hilton.com/en_US/hw/reservation/book.htm?inputModule=HOTEL_SEARCH&ctyhocn=DALTCHW&currencyCode=USD&internalDeepLinking=true";

            for (int i=0; i<100; i++) {

                URL obj = new URL(url);
                HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
                conn.setReadTimeout(50000);
                conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                conn.setInstanceFollowRedirects(false);
               conn.addRequestProperty("User-Agent", "Mozilla");
               conn.addRequestProperty("Cookie","mmapi.p.uat=%7B%22Layer%22%3A%22Phase_6%22%2C%22Cookied%22%3A%22Non-Cookied%22%2C%22loggedin%22%3A%22no%22%2C%22Referrer%22%3A%22Brand.com%22%2C%22SignInSource%22%3A%22Other%22%2C%22StayDuration%22%3A%221%20night%22%2C%22SatStay%22%3A%22no%22%2C%22DaysToBooking%22%3A%2215-30%22%2C%22Banner_Present%22%3A%22no%22%2C%22Brand%22%3A%22hi%22%2C%22Children%22%3A%22no%22%2C%22RoomNmbr%22%3A%221%22%2C%22FlexDates%22%3A%22false%22%2C%22hotelcode%22%3A%22no%22%2C%22UrgencyMsg%22%3A%22blank%22%2C%22HHDAP%22%3A%22Blank%22%2C%22SwapRates%22%3A%22Blank%22%2C%22PropCountry%22%3A%22USA%22%2C%22SRCount%22%3A%2255_Plus%22%2C%22Location%22%3A%22Downtown%20Plano%2C%20TX%22%2C%22NmbrRooms%22%3A%226%22%7D");
                //  conn.addRequestProperty("Referer", "google.com");
             //   conn.connect();

             //   System.out.println("Request URL ... " + url);

                boolean redirect = false;

                // normally, 3xx is redirect
                int status = conn.getResponseCode();
                System.out.println("Status ... " + status);

                if (status != HttpsURLConnection.HTTP_OK) {
                    if (status == HttpsURLConnection.HTTP_MOVED_TEMP
                            || status == HttpsURLConnection.HTTP_MOVED_PERM
                            || status == HttpsURLConnection.HTTP_SEE_OTHER)
                        redirect = true;
                }

              //  System.out.println("Response Code ... " + status);

                if (redirect) {

                    // get redirect url from "location" header field
                    String newUrl = conn.getHeaderField("Location");

                    // get the cookie if need, for login
                    String cookies = conn.getHeaderField("Set-Cookie");
                    System.out.println(cookies);

                    // open the new connnection again
                    conn = (HttpsURLConnection) new URL(newUrl).openConnection();
                    conn.setRequestProperty("Cookie", cookies);
                    conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                    conn.addRequestProperty("User-Agent", "Mozilla");
                    conn.addRequestProperty("Referer", "google.com");

                   System.out.println("Redirect to URL : " + newUrl);

                }

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer html = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    html.append(inputLine);
                }
                in.close();

          //     System.out.println("URL Content... \n" + html.toString());
           //     System.out.println("Done");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}