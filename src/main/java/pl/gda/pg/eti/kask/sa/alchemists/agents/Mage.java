package pl.gda.pg.eti.kask.sa.alchemists.agents;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.BuyStuffBehavior;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.FindServiceBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.MageBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.RequestHerbBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.RequestOfferBehavior;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.RequestPotionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.GiveOffer;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Herb;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Offer;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Potion;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellHerb;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellPotion;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellerInfo;

/**
 *
 * @author psysiu
 */
public class Mage extends BaseAgent {

    @Getter
    private ArrayList<SellerInfo> offers;
    
    @Getter
    private int herbNeeded = 8;
    
    @Getter
    private int potionNeeded = 8;

    public Mage() {
    }

    @Override
    protected void setup() {
        super.setup();
        this.offers = new ArrayList<>();
        
        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour(this);
        SequentialBehaviour gatherOffersBehaviour = new SequentialBehaviour(this);
        
        Behaviour findSellersBehaviour = new FindServiceBehaviour(this, "alchemist") {
            @Override
            protected void onResult(DFAgentDescription[] services) {
                if (services != null && services.length > 0) {
                    for(DFAgentDescription ad : services) {
                        AID alchemist = ad.getName();

                        /*
                        SellPotion action = new SellPotion(new Potion("Heroic Potion"));
                        RequestPotionBehaviour request = new RequestPotionBehaviour(Mage.this, alchemist, action);
                        ((SequentialBehaviour) getParent()).addSubBehaviour(request);
                         */
                        System.out.println("Requesting an offer from " + alchemist.getName());
                        GiveOffer giveOffer = new GiveOffer();
                        RequestOfferBehavior request = new RequestOfferBehavior(Mage.this, alchemist, giveOffer, offers);
                        gatherOffersBehaviour.addSubBehaviour(request);
                    }

                }
            }
        };
        
        sequentialBehaviour.addSubBehaviour(findSellersBehaviour);
        sequentialBehaviour.addSubBehaviour(gatherOffersBehaviour);
        sequentialBehaviour.addSubBehaviour(new BuyStuffBehavior(this));

        /*
        sequentialBehaviour.addSubBehaviour(new FindServiceBehaviour(this, "herbalist") {

            @Override
            protected void onResult(DFAgentDescription[] services) {
                if (services != null && services.length > 0) {
                    AID herbalist = services[0].getName();
                    SellHerb action = new SellHerb(new Herb("Peacebloom"));
                    RequestHerbBehaviour request = new RequestHerbBehaviour(Mage.this, herbalist, action);
                    ((SequentialBehaviour) getParent()).addSubBehaviour(request);

                }
            }
        });

        */
        
        addBehaviour(sequentialBehaviour);

        addBehaviour(new MageBehaviour(sequentialBehaviour, this));
    }

    public void handleOffer(Offer offer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
