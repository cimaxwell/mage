package mage.cards.c;

import mage.MageInt;
import mage.abilities.common.EntersBattlefieldTriggeredAbility;
import mage.abilities.common.SimpleStaticAbility;
import mage.abilities.dynamicvalue.common.InstantSorceryExileGraveyardCount;
import mage.abilities.effects.common.DrawCardSourceControllerEffect;
import mage.abilities.effects.common.continuous.SetPowerSourceEffect;
import mage.abilities.keyword.FlyingAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.constants.CardType;
import mage.constants.Duration;
import mage.constants.SubType;
import mage.constants.Zone;

import java.util.UUID;

/**
 * @author TheElk801
 */
public final class CracklingDrake extends CardImpl {

    public CracklingDrake(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.CREATURE}, "{U}{U}{R}{R}");

        this.subtype.add(SubType.DRAKE);
        this.power = new MageInt(0);
        this.toughness = new MageInt(4);

        // Flying
        this.addAbility(FlyingAbility.getInstance());

        // Crackling Drake's power is equal to the total number of instant and sorcery cards you own in exile and in your graveyard.
        this.addAbility(new SimpleStaticAbility(
                Zone.ALL,
                new SetPowerSourceEffect(
                        InstantSorceryExileGraveyardCount.instance, Duration.EndOfGame
                ).setText("{this}'s power is equal to the total number "
                        + "of instant and sorcery cards you own "
                        + "in exile and in your graveyard.")
        ));

        // When Crackling Drake enters the battlefield, draw a card.
        this.addAbility(new EntersBattlefieldTriggeredAbility(
                new DrawCardSourceControllerEffect(1)
        ));
    }

    private CracklingDrake(final CracklingDrake card) {
        super(card);
    }

    @Override
    public CracklingDrake copy() {
        return new CracklingDrake(this);
    }
}
