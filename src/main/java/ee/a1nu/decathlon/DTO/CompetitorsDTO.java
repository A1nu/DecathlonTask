package ee.a1nu.decathlon.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import ee.a1nu.decathlon.mapper.CompetitorMapper;
import ee.a1nu.decathlon.model.Competitor;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "competitors")
public class CompetitorsDTO {
    @XmlElement(name = "competitor")
    private List<CompetitorDTO> competitors;
    
    public CompetitorsDTO() {}
    
    public CompetitorsDTO(List<Competitor> competitors) {
        this.competitors = CompetitorMapper.mapCompetitors(competitors);
    }
}
