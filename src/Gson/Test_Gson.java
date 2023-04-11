package Gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Test_Gson {

    public static void main(String[] args) {

        try {
            String url = "https://image.baidu.com/search/albumsdata?pn=30&rn=30&tn=albumsdetail&word=%E5%9F%8E%E5%B8%82%E5%BB%BA%E7%AD%91%E6%91%84%E5%BD%B1%E4%B8%93%E9%A2%98&album_tab=%E4%BA%BA%E7%89%A9&album_id=43&ic=0&curPageNum=1";

            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(getHtml_toString(url));


            JsonObject oo = object.get("albumdata").getAsJsonObject();

            JsonArray array = oo.get("linkData").getAsJsonArray();

            for (int i = 0; i < array.size(); i++) {
                System.out.println("________________________");
                JsonObject suObject = array.get(i).getAsJsonObject();
                System.out.println("pid="+suObject.get("pid").getAsInt());
                System.out.println("thumbnailUrl="+suObject.get("thumbnailUrl").getAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void mkdir(String url){
        File file = new File("test");
        if (!file.exists()){
            file.mkdirs();
        }
        File ff = new File(file+"/test.json");
        FileWriter writer = null;

        try {

            if (!ff.exists()){
                ff.createNewFile();
            }
            writer = new FileWriter(ff,false);
            writer.append(getHtml_toString(url));
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getHtml_toString(String url){
        StringBuilder json = new StringBuilder();
        try {
            URL uu = new URL(url);
            URLConnection uc = uu.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String input = null;
            while ((input = reader.readLine()) !=null){
                json.append(input);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
