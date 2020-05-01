package mage.cards.b;

import java.util.UUID;
import mage.MageInt;
import mage.abilities.common.AttacksEachCombatStaticAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.SubType;

/**
 *
 * @author cimaxwell
 */
public final class Hulk  extends CardImpl {

    public Hulk(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.CREATURE},"{4}{R}");

        this.subtype.add(SubType.HUMAN, SubType.BERSERKER);
        this.power = new MageInt(4);
        this.toughness = new MageInt(4);
        this.addAbility(new AttacksEachCombatStaticAbility());
    }

    public Hulk(final Hulk card) {
        super(card);
    }

    @Override
    public Hulk copy() {
        return new Hulk(this);
    }

}
