public class Main {
    public static void main(String[] args) {
        ReadXMLUsingDOM dom = new ReadXMLUsingDOM();
        ReadXMLUsingDOM jdom = new ReadXMLUsingDOM();
        ReadXMLUsingDOM sax = new ReadXMLUsingDOM();
        dom.main(args);
        jdom.main(args);
        sax.main(args);
    }
}