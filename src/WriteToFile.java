import java.io.*;

public class WriteToFile {
    public static void writeDataToFile(Product[] products, String callingFunction) {
        String filePath = "";
        if ("ReadXMLUsingDOM".equals(callingFunction)) {
            filePath = "src/outputDOM.txt";
        } else if ("ReadXMLUsingJDOM".equals(callingFunction)) {
            filePath = "src/outputJDOM.txt";
        } else if ("ReadXMLUsingSAX".equals(callingFunction)) {
            filePath = "src/outputSAX.txt";
        }

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Product product : products) {
                writer.println("Product Name: " + product.getName());
                writer.println("Steel Brand: " + product.getSteelBrand());
                writer.println("Workshop: " + product.getWorkshop());

                writer.println("Parameters:");
                for (Parameter parameter : product.getParameters()) {
                    writer.println("  - Name: " + parameter.getName());
                    writer.println("    Value: " + parameter.getValue());
                    writer.println("    Unit: " + parameter.getUnit());
                }

                writer.println("Phisics Parameters:");
                for (PhysicsParameter physicsParameter : product.getPhysicsParameters()) {
                    writer.println("  - Name: " + physicsParameter.getName());
                    writer.println("    Value: " + physicsParameter.getValue());
                    writer.println("    Unit: " + physicsParameter.getUnit());
                }

                writer.println("Customer: " + product.getCustomer());
                writer.println("----------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}