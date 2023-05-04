package mappings.headers.app;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@XmlRootElement(name = "FileAppHdr")
@XmlAccessorType(XmlAccessType.FIELD)
public class FinancialInstId {
    @XmlElement(name = "BICFI")
    private String BICFI;
}
