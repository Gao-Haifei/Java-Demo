package Gson;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Copy_Html {
    public static void main(String[] args) {
        String url = "https://www.bilibili.com/video/BV1zS4y1K7Xa?from=search&seid=14902073938178481785&spm_id_from=333.337.0.0";
        Down(url);
    }

    public static void Down(String url){
        WebClient webClient = new WebClient(BrowserVersion.EDGE);

        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(10000);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());

        HtmlPage page;
        try {
            page = webClient.getPage(url);
            String Page_xml = page.asXml();

            File file = new File("test/test_html.html");
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            writer.append(Page_xml);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
