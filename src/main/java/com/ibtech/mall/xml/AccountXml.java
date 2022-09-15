package com.ibtech.mall.xml;

import com.ibtech.mall.core.XmlHelper;
import com.ibtech.mall.database.entity.Account;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class AccountXml {
    public static Document format(Account account) throws ParserConfigurationException {
        Document document = XmlHelper.create("account");
        Element element = document.getDocumentElement();
        formatDocumentElement(document, account, element);
        return document;
    }

    public static Document format(List<Account> products) throws ParserConfigurationException {
        Document document = XmlHelper.create("accounts");
        Element elementList = document.getDocumentElement();

        for (Account account : products) {
            Element element = document.createElement("account");
            formatDocumentElement(document, account, element);
            elementList.appendChild(element);
        }

        return document;

    }


    public static Account parse(Document document) {
        Element element = document.getDocumentElement();
        long accountId = XmlHelper.getSingleElementText(element, "accountId", 0);
        String accountName = XmlHelper.getSingleElementText(element, "accountName", "");
        String accountPassword = XmlHelper.getSingleElementText(element, "accountPassword", "");

        return new Account(accountId, accountName, accountPassword);
    }

    public static List<Account> parseList(Document document) {
        List<Account> newList = new ArrayList<>();
        Element elementList = document.getDocumentElement();
        NodeList nodeEntityList = elementList.getElementsByTagName("account");
        for (int i = 0; i < nodeEntityList.getLength(); i++) {
            Element element = (Element) nodeEntityList.item(i);
            long accountId = XmlHelper.getSingleElementText(element, "accountId", 0);
            String accountName = XmlHelper.getSingleElementText(element, "accountName", "");
            String accountPassword = XmlHelper.getSingleElementText(element, "accountPassword", "");
            Account account = new Account(accountId, accountName, accountPassword);
            newList.add(account);
        }
        return newList;
    }

    private static void formatDocumentElement(Document document, Account account, Element element) {
        XmlHelper.addSingleElementText(document, element, "accountId", account.getAccountId());
        XmlHelper.addSingleElementText(document, element, "accountName", account.getAccountName());
        XmlHelper.addSingleElementText(document, element, "accountPassword", account.getAccountPassword());

    }
}