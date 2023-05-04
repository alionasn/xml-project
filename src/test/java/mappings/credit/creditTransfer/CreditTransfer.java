package mappings.credit.creditTransfer;

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
@XmlRootElement(name = "CreditTransfer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreditTransfer {
    @XmlElement(name = "FIToFICstmrCdtTrf")
    private FToFCustomerTransfer fIToFICstmrCdtTrf;

}
