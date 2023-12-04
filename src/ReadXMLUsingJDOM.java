import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLUsingJDOM {
    public void main(String[] args) {
        try {
            File inputFile = new File("src/input.txt");
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            Element rootElement = document.getRootElement();
            List<Product> products = new ArrayList<>();

            List<Element> productList = rootElement.getChildren("product");
            for (Element productElement : productList) {
                Product product = new Product();
                product.setName(productElement.getChildText("Name"));
                product.setSteelBrand(productElement.getChildText("SteelBrand"));
                product.setWorkshop(productElement.getChildText("Workshop"));
                product.setCustomer(productElement.getChildText("Fabrica"));

                List<Element> parameterList = productElement.getChild("Parameters").getChildren("Parameter");
                List<Parameter> parameters = new ArrayList<>();
                for (Element parameterElement : parameterList) {
                    Parameter parameter = new Parameter();
                    parameter.setName(parameterElement.getChildText("Name"));
                    parameter.setValue(Double.parseDouble(parameterElement.getChildText("Value")));
                    parameter.setUnit(parameterElement.getChildText("Unit"));
                    parameters.add(parameter);
                }
                product.setParameters(parameters);

                List<Element> physicsParameterList = productElement.getChild("PhisicsParameters").getChildren("PhisicsParameter");
                List<PhysicsParameter> physicsParameters = new ArrayList<>();
                for (Element physicsParameterElement : physicsParameterList) {
                    PhysicsParameter physicsParameter = new PhysicsParameter();
                    physicsParameter.setName(physicsParameterElement.getChildText("Name"));
                    physicsParameter.setValue(Double.parseDouble(physicsParameterElement.getChildText("Value")));
                    physicsParameter.setUnit(physicsParameterElement.getChildText("Unit"));
                    physicsParameters.add(physicsParameter);
                }
                product.setPhysicsParameters(physicsParameters);

                products.add(product);
            }

            WriteToFile.writeDataToFile(products.toArray(new Product[0]), this.getClass().getSimpleName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}