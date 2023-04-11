package Jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class YQ {
    public static void main(String[] args) {
        String url = "http://sxwjw.shaanxi.gov.cn/sy/wjyw/202201/t20220110_2207014.html";
        Document document = Jsoup.parse(url);
        Elements element = document.getElementsByClass("view TRS_UEDITOR trs_paper_default trs_word");

        for (int i = 0; i < element.size(); i++) {
            String e = element.get(i).getElementsByAttribute("p").text();
            System.out.println(e);
        }


    }

}
