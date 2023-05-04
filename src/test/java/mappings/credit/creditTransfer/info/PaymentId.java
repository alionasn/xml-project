package mappings.credit.creditTransfer.info;

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
@XmlRootElement(name = "PmtId")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentId {
    @XmlElement(name = "InstrId")
    private String instrId;
    @XmlElement(name = "EndToEndId")
    private String e2eId;
    @XmlElement(name = "TxId")
    private String txId;
    @XmlElement(name = "ClrSysRef")
    private String clrSysRef;
}
