package ee.a1nu.decathlon.DTO;

import javax.xml.bind.annotation.XmlElement;

public class ResultPOJO {
    @XmlElement
    String name;
    @XmlElement
    String result;
    
    public ResultPOJO() {}
    
    public ResultPOJO(String name, String result) {
        this.name = name;
        this.result = result;
    }
}
