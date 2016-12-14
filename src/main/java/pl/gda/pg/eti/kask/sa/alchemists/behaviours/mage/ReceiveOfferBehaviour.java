package pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage;

import jade.content.Predicate;
import jade.content.onto.basic.Result;
import jade.core.AID;
import java.util.List;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ReceiveResultBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Offer;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellerInfo;

public class ReceiveOfferBehaviour extends ReceiveResultBehaviour<Mage> {
    private Mage mage;
    private final AID seller;
    private final List<SellerInfo> offers;

    public ReceiveOfferBehaviour(Mage agent, String conversationId, List<SellerInfo> offers, AID seller) {
        super(agent, conversationId);
        this.mage = agent;
        this.offers = offers;
        this.seller = seller;
    }

    @Override
    protected void handleResult(Predicate predicate, AID participant) {
        if (predicate instanceof Result) {
            Object value = ((Result) predicate).getValue();
            if(value instanceof Offer) {
                Offer offer = (Offer) value;
                System.out.println("Received the offer " + offer + " from " + participant);
                offers.add(new SellerInfo(this.seller, offer));
            } else {
                System.out.println("Result is not an offer!");
            }
        } else {
            System.out.println("No result");
        }
    }
    
}
