import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLUsingDOM {
    public void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("src/input.txt");
            NodeList productList = document.getElementsByTagName("product");

            List<Product> products = new ArrayList<>();

            for (int i = 0; i < productList.getLength(); i++) {
                if (productList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element productNode = (Element) productList.item(i);

                    String name = productNode.getElementsByTagName("Name").item(0).getTextContent();
                    String steelBrand = productNode.getElementsByTagName("SteelBrand").item(0).getTextContent();
                    String workshop = productNode.getElementsByTagName("Workshop").item(0).getTextContent();
                    String customer = productNode.getElementsByTagName("Fabrica").item(0).getTextContent();

                    List<Parameter> parameters = new ArrayList<>();
                    NodeList parametersNodeList = productNode.getElementsByTagName("Parameter");
                    for (int j = 0; j < parametersNodeList.getLength(); j++) {
                        Element parameterElement = (Element) parametersNodeList.item(j);
                        String paramName = parameterElement.getElementsByTagName("Name").item(0).getTextContent();
                        double paramValue = Double.parseDouble(parameterElement.getElementsByTagName("Value").item(0).getTextContent());
                        String paramUnit = parameterElement.getElementsByTagName("Unit").item(0).getTextContent();
                        Parameter parameter = new Parameter();
                        parameter.setName(paramName);
                        parameter.setValue(paramValue);
                        parameter.setUnit(paramUnit);
                        parameters.add(parameter);
                    }

                    List<PhysicsParameter> physicsParameters = new ArrayList<>();
                    NodeList physicsParametersNodeList = productNode.getElementsByTagName("PhisicsParameter");
                    for (int k = 0; k < physicsParametersNodeList.getLength(); k++) {
                        Element physicsParameterElement = (Element) physicsParametersNodeList.item(k);
                        String physicsParamName = physicsParameterElement.getElementsByTagName("Name").item(0).getTextContent();
                        double physicsParamValue = Double.parseDouble(physicsParameterElement.getElementsByTagName("Value").item(0).getTextContent());
                        String physicsParamUnit = physicsParameterElement.getElementsByTagName("Unit").item(0).getTextContent();
                        PhysicsParameter physicsParameter = new PhysicsParameter();
                        physicsParameter.setName(physicsParamName);
                        physicsParameter.setValue(physicsParamValue);
                        physicsParameter.setUnit(physicsParamUnit);
                        physicsParameters.add(physicsParameter);
                    }

                    Product product = new Product();
                    product.setName(name);
                    product.setSteelBrand(steelBrand);
                    product.setWorkshop(workshop);
                    product.setCustomer(customer);
                    product.setParameters(parameters);
                    product.setPhysicsParameters(physicsParameters);

                    products.add(product);
                }
            }

            WriteToFile.writeDataToFile(products.toArray(new Product[0]), this.getClass().getSimpleName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}