package mappings.credit.creditTransfer;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mappings.credit.settlement.SettlementInfo;

import java.util.Date;

@Data
@Getter
@Setter
@XmlRootElement(name = "GrpHdr")
@XmlAccessorType(XmlAccessType.FIELD)
public class GroupHeader {
    @XmlElement(name = "MsgId")
    private String messageId;
    @XmlElement(name = "CreDtTm")
    private Date creationDateTime;
    @XmlElement(name = "NbOfTxs")
    private Integer numOfTransactions;
    @XmlElement(name = "TtlIntrBkSttlmAmt")
    private TtlIntrBkSttlmAmt ttlIntrBkSttlmAmt;
    @XmlElement(name = "IntrBkSttlmDt")
    private String IntrBkSttlmDt;
    @XmlElement(name = "SttlmInf")
    private SettlementInfo settlementInfo;
}
