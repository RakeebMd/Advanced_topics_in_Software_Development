import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class Main {
    public static PartNumberDatabaseMock dbObj = new PartNumberDatabaseMock();
    public static PARTMANAGER result1 = new PARTMANAGERMock();
    public static Orders orderObject = new Orders();

    public static void main(String[] args) {
        try {
            boolean response = false;
            if (args.length == 0) {
                MainTest mainTest = new MainTest();
                mainTest.validateObjectsTest();
                mainTest.validatePartNumberTest();
            } else {
                File inputXMLFile = new File(args[0]);
                DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                Document document = documentBuilder.parse(inputXMLFile);
                if (document.hasChildNodes()) {
                    NodeList nodeContent = document.getChildNodes();
                    Orders orders = parseXMLFile(nodeContent);
                    String errorMessage = validateObjects(orders);
                    response = responseXMLOutput(orders, errorMessage);
                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("File Not Found Exception");
            throw new RuntimeException(e);
        } catch (SAXException e) {
            System.out.println("Invalid XML Format");
        }
    }

    private static Orders parseXMLFile(NodeList nodeList) {
        Dealer dealer_obj = new Dealer();
        Orderitems order_obj = new Orderitems();
        Deliveryaddress address_obj = new Deliveryaddress();
        ArrayList<Item> item_list_obj = new ArrayList<Item>();
        try {
            for (int count = 0; count < nodeList.getLength(); count++) {
                Node elemNode = nodeList.item(count);
                if (elemNode.getNodeType() == Node.ELEMENT_NODE) {
                    if (elemNode.getNodeName().equalsIgnoreCase("dealer")) {
                        Element dealer_element = (Element) elemNode;
                        dealer_obj.dealer_ID = dealer_element.getElementsByTagName("dealerid").item(0).getTextContent();
                        dealer_obj.dealer_access_key = dealer_element.getElementsByTagName("dealeraccesskey").item(0).getTextContent();
                        orderObject.dealer = dealer_obj;
                    } else if (elemNode.getNodeName().equalsIgnoreCase("orderitems")) {
                        NodeList order_list = elemNode.getChildNodes();
                        for (int i = 0; i < order_list.getLength(); i++) {
                            Node order_node = order_list.item(i);
                            if (order_node.getNodeName().equalsIgnoreCase("item")) {
                                Element order_items_element = (Element) order_node;
                                Item item_obj = new Item();
                                if (order_items_element.getElementsByTagName("partnumber").item(0).getTextContent().isEmpty() && order_items_element.getElementsByTagName("quantity").item(0).getTextContent().isEmpty()) {
                                    item_obj.part_number = 0;
                                    item_obj.quantity = 0;
                                } else if (order_items_element.getElementsByTagName("quantity").item(0).getTextContent().isEmpty()) {
                                    item_obj.part_number = Integer.parseInt(order_items_element.getElementsByTagName("partnumber").item(0).getTextContent());
                                    item_obj.quantity = 0;
                                } else if (order_items_element.getElementsByTagName("partnumber").item(0).getTextContent().isEmpty()) {
                                    item_obj.part_number = 0;
                                    item_obj.quantity = Integer.parseInt(order_items_element.getElementsByTagName("quantity").item(0).getTextContent());
                                } else {
                                    item_obj.part_number = Integer.parseInt(order_items_element.getElementsByTagName("partnumber").item(0).getTextContent());
                                    item_obj.quantity = Integer.parseInt(order_items_element.getElementsByTagName("quantity").item(0).getTextContent());
                                }
                                item_list_obj.add(item_obj);
                            }
                            order_obj.item = item_list_obj;
                            orderObject.orderitems = order_obj;
                        }
                    } else if (elemNode.getNodeName().equalsIgnoreCase("deliveryaddress")) {
                        Element address_element = (Element) elemNode;
                        address_obj.name = address_element.getElementsByTagName("name").item(0).getTextContent();
                        address_obj.street = address_element.getElementsByTagName("street").item(0).getTextContent();
                        address_obj.city = address_element.getElementsByTagName("city").item(0).getTextContent();
                        address_obj.province = address_element.getElementsByTagName("province").item(0).getTextContent();
                        address_obj.postal_code = address_element.getElementsByTagName("postalcode").item(0).getTextContent();
                        orderObject.deliveryaddress = address_obj;
                    }
                    if (elemNode.hasChildNodes()) {
                        parseXMLFile(elemNode.getChildNodes());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderObject;
    }

    public static String validateObjects(Orders obj) {
        String errorMessage = "";
        Security dealer_check = new SecurityMock();
        if (!dealer_check.IsDealerAuthorized(obj.dealer.dealer_ID, obj.dealer.dealer_access_key)) {
            errorMessage = "Not authorized";
        } else if (obj.orderitems.item.size() == 0) {
            errorMessage = "Invalid order item list";
        } else if (obj.deliveryaddress.city.trim().isEmpty() || obj.deliveryaddress.postal_code.trim().isEmpty() || obj.deliveryaddress.province.trim().isEmpty() || obj.deliveryaddress.street.trim().isEmpty()) {
            errorMessage = "Invalid delivery address";
        }
        for (int iter = 0; iter < obj.orderitems.item.size(); iter++) {
            if (obj.orderitems.item.get(iter).part_number != 0 && obj.orderitems.item.get(iter).quantity < 1) {
                errorMessage = "Invalid order item entry";
                return errorMessage;
            }
        }
        return errorMessage;
    }

    public static boolean responseXMLOutput(Orders order_obj, String errorMessage) {
        boolean response = false;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();
            Element order = doc.createElement("order");
            doc.appendChild(order);
            Element orderItems = doc.createElement("orderitems");
            Element result = doc.createElement("result");
            Element err_msg = doc.createElement("errormessage");
            Element error = doc.createElement("error");
            if (errorMessage.equalsIgnoreCase("Not authorized")) {
                result.appendChild(doc.createTextNode("failure"));
                error.appendChild(doc.createTextNode(errorMessage));
                order.appendChild(result);
                order.appendChild(error);
            } else if (errorMessage.equalsIgnoreCase("Invalid delivery address") ||
                    errorMessage.equalsIgnoreCase("Invalid order item list") ||
                    errorMessage.equalsIgnoreCase("Invalid order item entry")) {
                result.appendChild(doc.createTextNode("failure"));
                error.appendChild(doc.createTextNode("Invalid order"));
                err_msg.appendChild(doc.createTextNode(errorMessage));
                order.appendChild(result);
                order.appendChild(error);
                order.appendChild(err_msg);
            } else {
                PARTMANAGER result1 = new PARTMANAGERMock();
                for (int item_iter = 0; item_iter < order_obj.orderitems.item.size(); item_iter++) {
                    String result_check = "";
                    order.appendChild(orderItems);
                    Element item = doc.createElement("item");
                    Element part_number = doc.createElement("partnumber");
                    Element quantity = doc.createElement("quantity");
                    Element result_ele = doc.createElement("result");
                    Element err_msg1 = doc.createElement("errormessage");

                    if (dbObj.validatePartNumbers().contains(order_obj.orderitems.item.get(item_iter).part_number)) {
                        result_check = String.valueOf(result1.SubmitPartForManufactureAndDelivery(order_obj.orderitems.item.get(item_iter).part_number,
                                order_obj.orderitems.item.get(item_iter).quantity, order_obj.deliveryaddress));
                        if (result_check.equalsIgnoreCase("SUCCESS")) {
                            result_ele.appendChild(doc.createTextNode("success"));
                            err_msg1.appendChild(doc.createTextNode(errorMessage));
                        } else {
                            result_ele.appendChild(doc.createTextNode("failure"));
                            err_msg1.appendChild(doc.createTextNode(result_check));
                        }
                    } else {
                        result_ele.appendChild(doc.createTextNode("failure"));
                        err_msg1.appendChild(doc.createTextNode("invalid part"));
                    }
                    part_number.appendChild(doc.createTextNode(String.valueOf(order_obj.orderitems.item.get(item_iter).part_number)));
                    quantity.appendChild(doc.createTextNode(String.valueOf(order_obj.orderitems.item.get(item_iter).quantity)));
                    item.appendChild(part_number);
                    item.appendChild(quantity);
                    item.appendChild(result_ele);
                    item.appendChild(err_msg1);
                    orderItems.appendChild(item);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(System.out);
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
        return true;
    }

    public static ArrayList<String> validatePartNumbers(Orders order_obj, Item item) {
        String result_check;
        ArrayList<String> partResponse = new ArrayList<String>();
        if (dbObj.validatePartNumbers().contains(item.part_number)) {
            result_check = String.valueOf(result1.SubmitPartForManufactureAndDelivery(item.part_number, item.quantity, order_obj.deliveryaddress));
            if (result_check.equalsIgnoreCase("SUCCESS")) {
                partResponse.add("success");
                partResponse.add("");
            } else {
                partResponse.add("failure");
                partResponse.add(result_check);
            }
        } else {
            partResponse.add("failure");
            partResponse.add("invalid part");
        }
        return partResponse;
    }
}
