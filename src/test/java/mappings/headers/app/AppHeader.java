package mappings.headers.app;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.DateAdapter;

import java.util.Date;

@Data
@Getter
@Setter
@XmlRootElement(name = "AppHdr")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppHeader {
    @XmlElement(name = "FinInstnId")
    private FinancialInstId financialInstId;
    @XmlElement(name = "BizMsgIdr")
    private String bizMsgIdr;
    @XmlElement(name = "MsgDefIdr")
    private String msgDefIdr;
    @XmlElement(name = "CreDt")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date creationDate;
}
