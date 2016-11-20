/*
 * @author Imane ZEROUALI
 */
package imane.WebServlet;

import java.io.IOException;

import javax.websocket.Session;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.Duration;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class WSo {

    public void onnom(String nom, Session session) throws IOException,
            InterruptedException {
        boolean envoyer =true ;
        System.out.println("User input: " + nom);

        String[] cookies = nom.split("@");
        ArrayList<String[]> Informations = new ArrayList<String[]>();

        for(String c : cookies)
        {
          Informations.add(c.split("!"));
        }

        session.getBasicRemote().sendText(nom);
        while(envoyer == true)
        {
          ArrayList<String[]> datas = new ArrayList<String[]>();

          for(String[] c : Informations)
          {
            String[] data = c[1].split(" ");
            data[0] = c[0];
            data[1] = diff(data[1],data[2]);
            datas.add(data);
          }

          String text = toJson(datas);

          session.getBasicRemote().sendText(text);
          Thread.sleep(1000);
        }
    }

    public void onOpen() {
        System.out.println("Connecté");
    }

    public void onClose() {
        System.out.println("Deconnecté");
    }

    private String diff(String date, String locale){
    		String theDate = date;
    		String pattern = "MM-dd-yyyy//HH:mm:ss";
    		Date date2 = null;
    		try {
    			date2 = new SimpleDateFormat(pattern).parse(theDate);
    		} catch (ParseException e) {
    			return "erreur";
    		}

        LocalDateTime d2 = date2.toInstant().atZone(ZoneId.of(locale)).toLocalDateTime();
        LocalDateTime d1 = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        Duration duration = Duration.between(d1,d2);

        long diff = duration.getSeconds();
    		long diffSeconds = diff % 60;
    		long diffMinutes = diff / (60) % 60;
    		long diffHours = diff / (60 * 60) % 24;
    		long diffDays = diff / (24 * 60 * 60);
    		return diffDays+" jour(s) "+diffHours+" heure(s) "+diffMinutes+" minute(s) "+diffSeconds+" seconde(s)";

    	}

    private String toJson(ArrayList<String[]> datas)
    {
      int l = datas.size();
      String text = "[ ";
      int i=0;

      for(String[] d :datas)
      {
        if(i == l-1)
        {
          text +="{ "+"\"Id\": \""+d[0]+"\","+"\"Diff\": \""+d[1] +"\" }";
        }else {
          text += "{ "+"\"Id\": \""+d[0]+"\","+"\"Diff\": \""+d[1] +"\" },";
        }
        i++;
      }
      text += " ]";
      return text;
    }
}