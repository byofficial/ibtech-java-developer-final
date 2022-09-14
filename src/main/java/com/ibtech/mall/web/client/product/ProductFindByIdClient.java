package com.ibtech.mall.web.client.product;

import com.ibtech.mall.core.WebHelper;
import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Product;
import com.ibtech.mall.xml.ProductXml;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class ProductFindByIdClient {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        long playerId = 3;
        String address = String.format(System.getenv("SITE_URL") + "/api/product?id=%d", playerId);

        InputStream in = WebHelper.get(address);
        Document document = XmlHelper.parse(in);
        Product product = ProductXml.parse(document);

        System.out.println(product.getProductId() + " "
                + product.getProductName() + " "
                + product.getImagePath() + " "
                + product.getSalesPrice() + " "
                + product.getCategoryId());


    }
}
