package mappings.credit.settlement;

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
@XmlRootElement(name = "ClrSys")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClearingSystem {
    @XmlElement(name = "Cd")
    private String code;
}
