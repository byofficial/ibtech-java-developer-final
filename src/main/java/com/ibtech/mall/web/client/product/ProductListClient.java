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
import java.util.List;

public class ProductListClient {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        String address = System.getenv("SITE_URL") + "/api/products";
        InputStream in = WebHelper.get(address);
        Document document = XmlHelper.parse(in);
        List<Product> productList = ProductXml.parseList(document);

        for (Product product : productList) {
            System.out.println(product.getProductId() + " "
                    + product.getProductName() + " "
                    + product.getImagePath() + " "
                    + product.getSalesPrice() + " "
                    + product.getCategoryId());
        }
    }
}
