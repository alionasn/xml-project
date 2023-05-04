package mappings;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mappings.credit.creditTransfer.CreditTransfer;
import mappings.headers.FileHeader;
import mappings.headers.app.AppHeader;

@Getter
@Setter
@Data
@XmlRootElement(name = "Document")
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentXml {
    @XmlElement(name = "FileAppHdr")
    private FileHeader fileHeader;
    @XmlElement(name = "AppHdr")
    private AppHeader appHeader;
    @XmlElement(name = "CreditTransfer")
    private CreditTransfer creditTransfer;
}
