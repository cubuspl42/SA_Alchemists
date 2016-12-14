package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.core.AID;
import java.util.List;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ReceiveResultBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.RequestActionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.GiveOffer;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Offer;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellerInfo;

public class RequestOfferBehavior extends RequestActionBehaviour<GiveOffer, Mage> {

    private final List<SellerInfo> offers;

    public RequestOfferBehavior(Mage agent, AID participant, GiveOffer action, List<SellerInfo> offers) {
        super(agent, participant, action);
        this.offers = offers;
    }

    @Override
    protected ReceiveResultBehaviour createResultBehaviour(String conversationId) {
        return new ReceiveOfferBehaviour(myAgent, conversationId, offers, this.participant);
    }
    
}
