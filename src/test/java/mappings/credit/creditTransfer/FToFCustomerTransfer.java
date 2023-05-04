package mappings.credit.creditTransfer;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mappings.credit.creditTransfer.info.CreditTransfInfo;

import java.util.List;

@Data
@Getter
@Setter
@XmlRootElement(name = "FIToFICstmrCdtTrf")
@XmlAccessorType(XmlAccessType.FIELD)
public class FToFCustomerTransfer {
    @XmlElement(name = "GrpHdr")
    private GroupHeader groupHeader;
    @XmlElement(name = "CdtTrfTxInf")
    private List<CreditTransfInfo> transferInfoList;
}
