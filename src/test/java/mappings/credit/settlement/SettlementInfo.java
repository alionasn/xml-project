package mappings.credit.settlement;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.Methods;

@Data
@Getter
@Setter
@XmlRootElement(name = "SttlmInf")
@XmlAccessorType(XmlAccessType.FIELD)
public class SettlementInfo {
    @XmlElement(name = "SttlmMtd")
    private Methods methods;
    @XmlElement(name = "ClrSys")
    private ClearingSystem clearingSystem;
}
