package pl.gda.pg.eti.kask.sa.alchemists.ontology;

import jade.content.Concept;
import jade.core.AID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SellerInfo implements Concept {
    private AID aid;
    private Offer offer;
}
