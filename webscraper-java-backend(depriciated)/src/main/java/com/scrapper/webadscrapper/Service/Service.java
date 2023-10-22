package com.scrapper.webadscrapper.Service;

import java.io.IOException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.scrapper.webadscrapper.Model.Product;

@org.springframework.stereotype.Service
public class Service {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/118.0.0.0 Safari/537.36";

    Product product= new Product();
    public Product getProduct(String text) throws InterruptedException{

        try {
            Document doc = Jsoup.connect("https://google.com/search?q="+ URLEncoder.encode(text, "UTF-8")).userAgent(USER_AGENT).get();
            if(doc.getElementById("bGmlqc")==null){
                return null;
            }

            Element adContainer=((Element) 
                                    doc
                                    .getElementById("bGmlqc"))
                                    .getElementsByClass("mnr-c")
                                    .first()
                                    .child(2);

            
            if(adContainer.tagName()=="table"){
                product.setProductName(getProductNameTable(adContainer));

                product.setProductImgUrl(getProductImgUrlTable(adContainer));

                product.setProductPrice(getProductPriceTable(adContainer));
                return product;
            }
            
            product.setProductName(getProductName(adContainer));

            product.setProductImgUrl(getImageUrl(adContainer));

            product.setProductPrice(getProductPrice(adContainer));

           
        } catch (IOException e) {
            e.printStackTrace();
        }
        product.setProductName(text);


        return product;
    }

    public String getProductName(Element adContainer){
        String prodName=((Element) 
                            ((Element)adContainer
                                .child(2).firstChild()
                                .firstChild())
                                .child(1)
                                .firstChild())
                            .text();

        return prodName;
    }

    public String getImageUrl(Element adContainer){
         String imgUrl=((Element)
                            adContainer
                                .child(1)
                                .child(1)
                                .firstChild()
                                .firstChild())
                                .child(1)
                                .absUrl("src");

            return imgUrl;
    }

    public String getProductPrice(Element adContainer){
        Element prodPrice=((Element)
                            adContainer
                                .child(2)
                                .firstChild())
                                .child(1);
        while(prodPrice.firstChild().firstChild()!=null){
            prodPrice=(Element) prodPrice.firstChild();
        }
                                
        String price=prodPrice.text();
        
        return price;
    }


    public String getProductImgUrlTable(Element adContainer){
        String prodImg=((Element) ((Element) adContainer
                            .firstChild()
                            .firstChild()
                            .firstChild()
                            .firstChild())
                            .child(1)
                            .firstChild())
                            .child(1)
                            .attr("src");

        return prodImg;
    }


    public String getProductNameTable(Element adContainer){
        String prodName=((Element) ((Element) ((Element) adContainer
                            .firstChild()
                            .firstChild())
                            .child(1)
                            .firstChild()
                            .firstChild())
                            .child(1)
                            .firstChild()).text();

        return prodName;
    }


    public String getProductPriceTable(Element adContainer){
        String prodPrice=((Element) ((Element) ( (Element) adContainer
                            .firstChild()
                            .firstChild())
                            .child(1)
                            .firstChild())
                            .child(1)
                            .firstChild()).text();

        return prodPrice;
    }

}
