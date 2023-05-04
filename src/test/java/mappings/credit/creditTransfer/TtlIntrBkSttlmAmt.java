package mappings.credit.creditTransfer;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@XmlRootElement(name = "TtlIntrBkSttlmAmt")
@XmlAccessorType(XmlAccessType.FIELD)
public class TtlIntrBkSttlmAmt {
    @XmlAttribute(name = "Ccy")
    private String ccy;
    private Double amount;
}
