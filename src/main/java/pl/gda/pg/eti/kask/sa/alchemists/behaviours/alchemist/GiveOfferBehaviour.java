package pl.gda.pg.eti.kask.sa.alchemists.behaviours.alchemist;

import jade.content.Predicate;
import jade.content.onto.basic.Result;
import jade.core.AID;
import java.util.Random;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Alchemist;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.ActionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.GiveOffer;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Offer;

/**
 *
 * @author psysiu
 */
public class GiveOfferBehaviour extends ActionBehaviour<GiveOffer, Alchemist> {

    public GiveOfferBehaviour(Alchemist agent, GiveOffer action, String conversationId, AID participant) {
        super(agent, action, conversationId, participant);
    }

    @Override
    protected Predicate performAction() {
        return new Result(action, new Offer("OFFER", new Random().nextInt() % 1024));
    }
}
