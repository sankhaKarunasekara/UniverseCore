package lk.universe.core.util;

import lk.universe.core.factory.ExtractMethodsFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLReader {

    private String url;
    private String userAgent;

    public HTMLReader(String url) {
        this.url = url;
    }

    public Document getHTMLPage() {
        Document doc = null;
        try {
            doc = Jsoup.connect(this.url).get();
        } catch (Exception e) {
            return doc;
        }
        return doc;
    }

    public String getHTMLPageAsString() {

        Document doc;
        String userAgent = "Mozilla";

        if (this.userAgent != null) {
            userAgent = this.userAgent;
        }

        try {
            doc = Jsoup.connect(this.url).userAgent(userAgent).get();
        } catch (Exception e) {
            return "";
        }
        return doc.text();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public static void main(String[] args) throws IOException {

        //Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        ExtractMethodsFactory extractMethodsFactory = new ExtractMethodsFactory();
        String url = "https://www.exchange-rates.org/history/LKR/USD/G/30";
        String centralBankUrl = "https://www.cbsl.gov.lk/cbsl_custom/charts/usd/oneyear.php";
        String centralBankBuyingSellingInductive = "https://www.cbsl.gov.lk/cbsl_custom/charts/usd/indexsmall.php";

        url = centralBankUrl;

        Util.print("Fetching %s...", url);

        Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (compatible; MSIE 9.0; Windows Phone OS 7.5; Trident/5.0; IEMobile/9.0)").get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        Util.print("\nMedia: (%d)", media.size());

        //print("whole Test:"+ doc.text());

        try {
            System.out.println("getMap");
            Util.print("Map: " + extractMethodsFactory.getMapfromDataString(doc.text()).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Element src : media) {
            if (src.tagName().equals("img"))
                Util.print(" * %s: <%s> %sx%s (%s)",
                        src.tagName(), src.attr("abs:src"), src.attr("width"), src.attr("height"),
                        Util.trim(src.attr("alt"), 20));
            else
                Util.print(" * %s: <%s>", src.tagName(), src.attr("abs:src"));
        }

        Util.print("\nImports: (%d)", imports.size());
        for (Element link : imports) {
            Util.print(" * %s <%s> (%s)", link.tagName(), link.attr("abs:href"), link.attr("rel"));
        }

        Util.print("\nLinks: (%d)", links.size());
        for (Element link : links) {
            Util.print(" * a: <%s>  (%s)", link.attr("abs:href"), Util.trim(link.text(), 35));
        }
    }
}