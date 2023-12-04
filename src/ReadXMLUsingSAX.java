import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLUsingSAX {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                boolean isProduct = false;
                boolean isParameter = false;
                boolean isPhysicsParameter = false;
                Product product = null;
                Parameter parameter = null;
                PhysicsParameter physicsParameter = null;
                List<Product> products = new ArrayList<>();

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("product")) {
                        isProduct = true;
                        product = new Product();
                    } else if (qName.equalsIgnoreCase("parameter")) {
                        isParameter = true;
                        parameter = new Parameter();
                    } else if (qName.equalsIgnoreCase("phisicsParameter")) {
                        isPhysicsParameter = true;
                        physicsParameter = new PhysicsParameter();
                    }
                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (isProduct) {
                        String data = new String(ch, start, length).trim();
                        if (!data.isEmpty()) {
                            if (isParameter) {
                                if (parameter.getName() == null) {
                                    parameter.setName(data);
                                } else if (parameter.getValue() == 0.0) {
                                    parameter.setValue(Double.parseDouble(data));
                                } else {
                                    parameter.setUnit(data);
                                }
                            } else if (isPhysicsParameter) {
                                if (physicsParameter.getName() == null) {
                                    physicsParameter.setName(data);
                                } else if (physicsParameter.getValue() == 0.0) {
                                    physicsParameter.setValue(Double.parseDouble(data));
                                } else {
                                    physicsParameter.setUnit(data);
                                }
                            } else {
                                switch (data) {
                                    case "Product 1":
                                        product.setName(data);
                                        break;
                                    case "Carbon":
                                        product.setSteelBrand(data);
                                        break;
                                    case "Division 1":
                                        product.setWorkshop(data);
                                        break;
                                    case "Customer 1":
                                        product.setCustomer(data);
                                        break;
                                }
                            }
                        }
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("product")) {
                        isProduct = false;
                        products.add(product);
                    } else if (qName.equalsIgnoreCase("parameter")) {
                        isParameter = false;
                        product.getParameters().add(parameter);
                    } else if (qName.equalsIgnoreCase("phisicsParameter")) {
                        isPhysicsParameter = false;
                        product.getPhysicsParameters().add(physicsParameter);
                    }
                }

                public void endDocument() throws SAXException {
                    WriteToFile.writeDataToFile(products.toArray(new Product[0]), this.getClass().getSimpleName());
                }
            };

            saxParser.parse("src/input.txt", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}