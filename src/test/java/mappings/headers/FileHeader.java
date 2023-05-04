package mappings.headers;

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
public class FileHeader {

    @XmlElement(name = "NbOfBtchs")
    private Integer noOfBatches;
    @XmlElement(name = "SttlmCycl")
    private Integer settlementCycle;
}
