package pl.gda.pg.eti.kask.sa.alchemists.behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.sa.alchemists.agents.Mage;
import pl.gda.pg.eti.kask.sa.alchemists.behaviours.mage.RequestPotionBehaviour;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.Potion;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellPotion;
import pl.gda.pg.eti.kask.sa.alchemists.ontology.SellerInfo;

@Log
public class BuyStuffBehavior extends OneShotBehaviour {

    private Mage mage;

    public BuyStuffBehavior(Mage agent) {
        super(agent);
        this.mage = agent;
    }

    @Override
    public void action() {
        System.out.println("Buying stuff!");
        List<SellerInfo> offers = this.mage.getOffers();
        
        System.out.println("Buying herb...");
        offers.sort(new Comparator<SellerInfo>() {
            @Override
            public int compare(SellerInfo o1, SellerInfo o2) {
                return Integer.compare(
                        o1.getOffer().getHerbPrice(),
                        o2.getOffer().getHerbPrice());
            }
        });
        
        int herbNeeded = this.mage.getHerbNeeded();
        for(SellerInfo si : offers) {
            System.out.println(si);
     
            System.out.println("Buy 1 potion...");
            SellPotion action = new SellPotion(new Potion("Heroic Potion"));
            RequestPotionBehaviour request = new RequestPotionBehaviour(this.mage, si.getAid(), action);
            this.mage.addBehaviour(request);
        }

    }
}
