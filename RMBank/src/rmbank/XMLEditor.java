/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmbank;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import rmbank.database.BankDB;

/**
 *
 * @author Rolo
 */
public class XMLEditor {
    
    private int customerID;
    
    public XMLEditor (int customerID) {
        this.customerID = customerID;
    }
    
    private List<Account> listAccounts() throws Exception {
        return new BankDB().getAccountsByCustomer(new BankDB().getCustomerByID(customerID));
    }
    
    private int getCardCount(int accNum) throws Exception {
        int count = 0;
        for (Card c : new BankDB().getCards(customerID)) {
            if (c.getAccnum() == accNum) count++;
        }
        return count;
    }
    
    public void createFile() {
        try {
            String customerName = new BankDB().getCustomerByID(customerID)
                    .getLastName()+" "+new BankDB().
                    getCustomerByID(customerID).getFirstName();
            List<Account> accounts = listAccounts();
            String filePath = System.getProperty("user.dir")+"\\"+customerName+".xml";
            ///
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            
            Element rootElement = doc.createElement("accounts");
            doc.appendChild(rootElement);
            
            if (!(accounts == null || accounts.size() <= 0)) {
                for (Account a : accounts) {
                    Element account = doc.createElement("account");
                    rootElement.appendChild(account);
                    account.setAttribute("currency", "EUR");
                    
                    Element number = doc.createElement("number");
                    number.appendChild(doc.createTextNode(String.valueOf(a.getNumber())));
                    account.appendChild(number);
                    
                    Element balance = doc.createElement("balance");
                    balance.appendChild(doc.createTextNode(String.valueOf(a.getBalance())));
                    account.appendChild(balance);
                    
                    Element cards = doc.createElement("cards");
                    cards.appendChild(doc.createTextNode(String.valueOf(getCardCount(a.getNumber()))));
                    account.appendChild(cards);
                }
            }
            
            System.out.println("accountCount="+accounts.size());
            System.out.println(rootElement.toString());
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            
            DOMSource source = new DOMSource(doc);

            StreamResult result =  new StreamResult(new File(filePath));
            transformer.transform(source, result);
            System.out.println(filePath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
