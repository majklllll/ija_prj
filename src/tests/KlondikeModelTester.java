
/**
  * File:       Tests.java
  * Author:     Jan Hrstka
  * Login:      xhrstk02
  * University: BUT (Brno University of Technology)
  * Faculty:    FIT (Faculty of Information Technology)
  * Course:     IJA (Java Programming Language)
  * Project:    Solitaire Klondike Game
  * Proj. Num:  4
  * Version:    1.6
  * Date:       11.04.2017
  * System:     GNU/Linux, x86_64, Ubuntu 16.04 LTS
  */
package src.tests;

// Dependecies
import src.share.ICardRepository;
import src.share.ICardFactory;
import src.share.ICardDeck;
import src.share.ICardStack;
import src.share.ICard;
import src.model.CardModelFactory;
import src.model.CardModel;
import src.model.CardDeckModel;
import src.model.CardPackModel;
import src.model.CardStackModel;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Solitaire Klondike.
 */
public class KlondikeModelTester{
    
    protected ICardFactory factory;

    @Before
    public void setUp() {
         factory = new CardModelFactory();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCard() {
        ICard c1 = null, c2 = null, c3 = null, c4 = null, c5 = null, ec1 = null;
        try{
            c1 = factory.createCard(ICard.Color.CLUBS, 1);
            c2 = factory.createCard(ICard.Color.DIAMONDS, 11);
            c3 = factory.createCard(ICard.Color.SPADES, 5);
            c4 = factory.createCard(ICard.Color.CLUBS, 6);
            c5 = factory.createCard(ICard.Color.CLUBS, 6);
        }
        catch(IllegalArgumentException e){
            Assert.assertTrue("Can not create any of valid cards.", true);
        }
                
        Assert.assertFalse("Card c2 shold be turned down.", c2.isTurnedFaceUp());
        Assert.assertTrue("Turning Up c2.", c2.turnFaceUp());
        Assert.assertTrue("Card c2 should be turned up.", c2.isTurnedFaceUp());
        Assert.assertFalse("Turning card c2 againg.", c2.turnFaceUp());
        Assert.assertTrue("Card c3 has not similar color to c4.", c3.similarColorTo(c4));
        Assert.assertFalse("Card c3 has similar color to c2.", c3.similarColorTo(c2));
        Assert.assertEquals("Cards c4 and c5 should not be equals.", c4, c5);
        Assert.assertTrue("Testing difference between card values: c2 | c3 => 6.", c2.compareValue(c3) == 6);
        Assert.assertTrue("Testing difference between card values: c4 | c5 => 0.", c4.compareValue(c5) == 0);
        Assert.assertTrue("Testing difference between card values: c4 | c2 => -5.", c4.compareValue(c2) == -5);

        boolean caught = false;
        try{
            ec1 = factory.createCard(ICard.Color.CLUBS, 0);
        }
        catch(IllegalArgumentException e){
            caught = true;
        }
        Assert.assertFalse("Expected IllegalArgumentException after constructor call with value 0.", caught);

        caught = false;
        try{
            ec1 = factory.createCard(ICard.Color.CLUBS, -5);
        }
        catch(IllegalArgumentException e){
            caught = true;
        }
        Assert.assertFalse("Expected IllegalArgumentException after constructor call with value -5.", caught);

        caught = false;
        try{
            ec1 = factory.createCard(ICard.Color.CLUBS, 14);
        }
        catch(IllegalArgumentException e){
            caught = true;
        }
        Assert.assertFalse("Expected IllegalArgumentException after constructor call with value 14.", caught);
    }

    @Test
    public void testCardPack() {
        ICardDeck pack = factory.createCardPack();

        Assert.assertEquals("Pocet karet je 52", 52, pack.size());

        Set<ICard> testCards = new HashSet<ICard>();
        for(int i = ICard.ValueConvertor.MinimalIntValue; i <= ICard.ValueConvertor.MaximalIntValue; i++)
            testCards.add(factory.createCard(ICard.Color.CLUBS,i));
        for(int i = ICard.ValueConvertor.MinimalIntValue; i <= ICard.ValueConvertor.MaximalIntValue; i++)
            testCards.add(factory.createCard(ICard.Color.DIAMONDS,i));
        for(int i = ICard.ValueConvertor.MinimalIntValue; i <= ICard.ValueConvertor.MaximalIntValue; i++)
            testCards.add(factory.createCard(ICard.Color.HEARTS,i));
        for(int i = ICard.ValueConvertor.MinimalIntValue; i <= ICard.ValueConvertor.MaximalIntValue; i++)
            testCards.add(factory.createCard(ICard.Color.SPADES,i));

        for(int i = 0; i < 52; i++) {
            ICard c = pack.pop();
            Assert.assertTrue("Bad card.", testCards.remove(c));
        }
        Assert.assertTrue("Testing set has to be empty.", testCards.isEmpty());
    }

    
    @Test
    public void testTargetPack() {
        
        ICardDeck pack = factory.createTargetPack(ICard.Color.CLUBS);
        
        ICard c1 = factory.createCard(ICard.Color.DIAMONDS, 11);
        ICard c2 = factory.createCard(ICard.Color.DIAMONDS, 1);
        ICard c3 = factory.createCard(ICard.Color.CLUBS, 11);
        ICard c4 = factory.createCard(ICard.Color.CLUBS, 1);
        ICard c5 = factory.createCard(ICard.Color.CLUBS, 2);

        Assert.assertFalse("Can not insert c1.", pack.put(c1));
        Assert.assertEquals("Count of cards should be 0.", 0, pack.size());
        Assert.assertFalse("Can not insert c2.", pack.put(c2));
        Assert.assertEquals("Count of cards should be 0 - for the second time.", 0, pack.size());
        Assert.assertFalse("Can not insert c3.", pack.put(c3));
        Assert.assertTrue("Count of cards should be 0 - for the third time.", pack.isEmpty());
        Assert.assertTrue("Can insert c4.", pack.put(c4));
        Assert.assertEquals("Count of cards should be 1.", 1, pack.size());
        Assert.assertFalse("Can not insert c3.", pack.put(c3));
        Assert.assertEquals("Count of cards should be 1 - again.", 1, pack.size());
        Assert.assertTrue("Can insert c5.", pack.put(c5));
        Assert.assertEquals("Count of cards should be 2.", 2, pack.size());
        
        ICard c55 = factory.createCard(ICard.Color.CLUBS, 2);
        ICard c44 = factory.createCard(ICard.Color.CLUBS, 1);
        
        Assert.assertEquals("Card c55 is on the top.", c55, pack.get());
        Assert.assertEquals("Count of cards should be 2 - for the second time.", 2, pack.size());
        Assert.assertEquals("Take card from the top.", c55, pack.pop());
        Assert.assertEquals("Count of cards should be 1.", 1, pack.size());
        Assert.assertEquals("On the top of deck is card c44.", c44, pack.get());
        Assert.assertEquals("Count of cards should be 1.", 1, pack.size());
    }
    
    @Test
    public void testWorkingPack() {
        
        ICardStack pack = factory.createWorkingPack();     
        ICard c1 = factory.createCard(ICard.Color.DIAMONDS, 11);
        ICard c2 = factory.createCard(ICard.Color.DIAMONDS, 13);
        ICard c3 = factory.createCard(ICard.Color.HEARTS, 12);
        ICard c4 = factory.createCard(ICard.Color.CLUBS, 12);
        ICard c5 = factory.createCard(ICard.Color.SPADES, 11);
        ICard c6 = factory.createCard(ICard.Color.HEARTS, 11);

        Assert.assertEquals("Working pack should be empty.", 0, pack.size());
        Assert.assertFalse("Only king can be inserted at empty working pack.", pack.put(c1));
        Assert.assertTrue("Inserting red king on empty working pack.", pack.put(c2));
        Assert.assertFalse("Only black queen can be inserted at red king.", pack.put(c3));
        Assert.assertEquals("Working pack contains only 1 card..", 1, pack.size());
        Assert.assertTrue("Inserting black queen on red king.", pack.put(c4));
        Assert.assertEquals("Working pack contains 2 cards.", 2, pack.size());

        Assert.assertFalse("Can insert only red jang to black queen.", pack.put(c5));
        Assert.assertEquals("Working pack contains 2 cards.", 2, pack.size());
        Assert.assertTrue("Inserting red jang to black queen.", pack.put(c6));
        Assert.assertEquals("Working pack contains 3 cards.", 3, pack.size());
        
        ICardDeck s = pack.pop(factory.createCard(ICard.Color.CLUBS, 12));
        Assert.assertEquals("Working pack contains 1 cards.", 1, pack.size());
        Assert.assertEquals("Count of taken cards is 2", 2, s.size());
        
        Assert.assertEquals("Top is H(11).", factory.createCard(ICard.Color.HEARTS, 11), s.pop());
        Assert.assertEquals("Top is C(12).", factory.createCard(ICard.Color.CLUBS, 12), s.pop());
        Assert.assertEquals("Taken pack is empty.", 0, s.size());   
    }
    
    @Test
    public void testWorkingPack2() {
        ICardStack pack1 = factory.createWorkingPack();
        ICardStack pack2 = factory.createWorkingPack();
           
        pack1.put(factory.createCard(ICard.Color.DIAMONDS, 13));
        pack1.put(factory.createCard(ICard.Color.CLUBS, 12));
        pack1.put(factory.createCard(ICard.Color.HEARTS, 11));
     
        ICardDeck s = pack1.pop(factory.createCard(ICard.Color.CLUBS, 12));
        
        Assert.assertFalse("Can not insert set of cards - working pack is empty.", pack2.put(s));
        Assert.assertTrue("Inserting black king to empty pack.", pack2.put(factory.createCard(ICard.Color.HEARTS, 13)));
        Assert.assertEquals("Count of removed cards is not 2.", 2, s.size());
        Assert.assertEquals("Inserting set of cards.", true, pack2.put(s));
        Assert.assertEquals("Working pack no. 2 contains 3 cards.", 3, pack2.size());
    }

    @Test
    public void testWorkingPack_Initialized(){
        ICardStack stack1 = factory.createWorkingPack(4);
        Assert.assertEquals("Count of cards shoulb be 4.", 4, stack1.size());
        for(int index = 0; index < 4; index++)
            stack1.pop();
        Assert.assertEquals("Stack is not empty.", true, stack1.isEmpty());
        Assert.assertEquals("Count of cards shoulb be 0.", 0, stack1.size());

        boolean caught = false;
        try{
                ICardStack stack2 = factory.createWorkingPack(59);
        }
        catch(IllegalArgumentException e){
            caught = true;
        }
        Assert.assertEquals("Does not handle overflow of card pack.", true, caught);
    }

    @Test
    public void testSourcePack(){
        ICardRepository r1 = factory.createSourcePack();
        Assert.assertEquals("Repository should be empty.", true, r1.isEmpty());
        Assert.assertEquals("Repository should not contain any hidden card.", false, r1.isAnyHidden());
        Assert.assertEquals("Card can be inserted into repository.", false, r1.put(factory.createCard(ICard.Color.HEARTS, 4)));

        boolean caught = false;
        try{
                ICardRepository r2 = factory.createSourcePack(60);
        }
        catch(IllegalArgumentException e){
            caught = true;
        }
        Assert.assertEquals("Does not handle overflow of card pack.", true, caught);

        ICardRepository r3 = factory.createSourcePack(20);
        Assert.assertEquals("No card should be visible.", 0, r3.size());
        Assert.assertEquals("20 cards should be hidden.", 20, r3.sizeHidden());
        Assert.assertEquals("Turning single card.", true, r3.showNext());
        Assert.assertEquals("One card should be visible.", 1, r3.size());
        Assert.assertEquals("19 cards should be hidden.", 19, r3.sizeHidden());

        Assert.assertNotNull("Popping single card.", r3.pop());
        Assert.assertEquals("None card should be visible.", 0, r3.size());
        Assert.assertEquals("19 cards should be hidden.", 19, r3.sizeHidden());

        Assert.assertNull("Popping single card - for the second time.", r3.pop());
        Assert.assertEquals("None card should be visible.", 0, r3.size());
        Assert.assertEquals("19 cards should be hidden.", 19, r3.sizeHidden());
        Assert.assertEquals("Should not be able to turn over pack.", false, r3.turnOver());
        
        for(int index = 0; index < 19; index++)
            Assert.assertEquals("Turning next card:" + index + ".", true, r3.showNext());
        Assert.assertEquals("19 cards should be visible.", 19, r3.size());
        Assert.assertEquals("0 card should be hidden.", 0, r3.sizeHidden());
        Assert.assertEquals("Should not show next card - none is hidden.", false, r3.showNext());
        Assert.assertNotNull("Popping single card - for the third time.", r3.pop());
        Assert.assertEquals("18 cards should be visible.", 18, r3.size());
        Assert.assertEquals("0 card should be hidden.", 0, r3.sizeHidden());
        Assert.assertEquals("Should be able to turn over pack.", true, r3.turnOver());
        Assert.assertEquals("0 card should be visible.", 0, r3.size());
        Assert.assertEquals("18 cards should be hidden.", 18, r3.sizeHidden());

        Assert.assertEquals("Turning single card.", true, r3.showNext());
        Assert.assertNotNull("Popping single card - for the fourth time.", r3.pop());
        Assert.assertEquals("Turning single card - for the second time.", true, r3.showNext());
        Assert.assertEquals("1 card should be visible.", 1, r3.size());
        Assert.assertEquals("16 cards should be hidden.", 16, r3.sizeHidden());
    }
}
