package Jsoup;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HtmlUnit_Test {

    public List<Content> JD_Html(String url) throws Exception {
        Document document = Jsoup.parse(new URL("https://search.jd.com/Search?keyword="+url),30000);
        Element element = document.getElementById("J_goodsList");
        Elements all = element.getElementsByTag("li");

        ArrayList<Content> list = new ArrayList<>();
        for (Element e : all){
            String price = e.getElementsByClass("p-price").eq(0).text();
            String img = e.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String title = e.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setTitle(title);
            content.setImg(img);
            content.setPrice(price);

            list.add(content);
        }
        return list;
    }


    public static void main(String[] args) throws Exception {
        new HtmlUnit_Test().JD_Html("Java").forEach(System.out::println);

        List<String> imgList = new ArrayList<>();
        List<String> imgName = new ArrayList<>();

        WebClient webClient = new WebClient(BrowserVersion.EDGE);

        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.waitForBackgroundJavaScript(10000);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());


        /*      模拟Html点击!!!!!!!!!!!      */
//        try {
//            HtmlPage page = webClient.getPage("https://www.baidu.com/");
//            HtmlForm form = page.getFormByName("f");
//            HtmlSubmitInput button = form.getInputByValue("百度一下");
//            HtmlTextInput input = form.getInputByName("wd");
//
//            input.setValueAttribute("美女");
//
//            HtmlPage page_2 = button.click();
//            System.out.println(page_2.asText());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /*      抓取百度图片!!!!!!!!!!!      */
//        try {
//            HtmlPage page = webClient.getPage("https://image.baidu.com/search/albumsdetail?tn=albumsdetail&word=%E4%BA%BA%E7%89%A9&fr=albumslist&album_tab=%E4%BA%BA%E7%89%A9&album_id=43&rn=30");
//            String Page_xml = page.asXml();
//
//            Document document = Jsoup.parse(Page_xml);
//            Element element = document.getElementById("imgList");
//
//
//            if (element != null){
//                Elements elements = element.getElementsByClass("albumsdetail-column");
//                for (Element value : elements) {
//                    Elements ee = value.getAllElements();
//                    if (ee.size() != 0) {
//                        String aa = "";
//                        for (Element e : ee) {
//
//                            String a = e.getElementsByTag("img").attr("src");
//                            if (!aa.equals(a)) {
//                                imgList.add(a);
//                                System.out.println(a);
//                            }
//                            aa = a;
//
//                        }
//                    }
//
//                }
//
//                Write_img(imgList);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }

        /*      抓取垃圾网址图片!!!!!!!!!!!      */
//        try {
//
//            HtmlPage page = webClient.getPage("https://www.toopic.cn/4kbz/?q=81----.html");
//            String Page_xml = page.asXml();
////
////            HtmlForm mForm = (HtmlForm) page.getByXPath("/html/body/div[2]/div[2]/div[1]/div/div/form").get(0);
////            HtmlTextInput username = mForm.getInputByName("username");
////            HtmlTextInput password = mForm.getInputByName("password");
////            HtmlSubmitInput submit = mForm.getInputByValue("登录");
////            username.setValueAttribute("17691115647");
////            password.setValueAttribute("hf200080");
////            HtmlPage page_2 =  submit.click();
////            String page_2_xml = page_2.asXml();
//
//            Document document = Jsoup.parse(Page_xml);
//            Elements element = document.getElementsByClass("clearfix pic-list gallery");
//            if (element.size() != 0) {
//                Elements elements = element.get(0).getElementsByTag("li");
//
//                for (Element ee : elements) {
//                    String href = ee.getElementsByTag("img").attr("src");
//                    String img_name = ee.getElementsByTag("img").attr("alt");
//                    if (!href.equals("/templets/images/d7.gif")) {
//                        String img_url = "https://www.toopic.cn" + href;
//                        System.out.println(img_url);
//                        System.out.println(img_name);
//                        imgName.add(img_name);
//                        imgList.add(img_url);
//                    }
//                }
//                Write_img(imgList,imgName);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//
//        }
    }


    public static void Read_img(String filepath, String filename) {

    }

    public static void Write_img(List<String> imgList,List<String> imgname) {
        try {
            int i = 0;
            for (String url : imgList) {

                if (url.equals("")) {
                    continue;
                }

                String img_name = imgname.get(i)+".jpg";
                File file = new File("D:\\MicroSoft_Image\\" + img_name);
                URL uri = new URL(url);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length;
                System.out.println("开始下载 " + uri);

                while ((length = in.read(bytes, 0, bytes.length)) != -1) {
                    fo.write(bytes, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(img_name + "下载完成");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("下载失败");
        }
    }

}
