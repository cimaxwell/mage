package mage.abilities.common;

import mage.abilities.TriggeredAbilityImpl;
import mage.abilities.effects.Effect;
import mage.constants.Zone;
import mage.game.Game;
import mage.game.events.GameEvent;
import mage.game.permanent.Permanent;

import static mage.constants.CardType.CREATURE;

public class AttachedToCreatureTriggeredAbility extends TriggeredAbilityImpl {

    public AttachedToCreatureTriggeredAbility(Effect effect, boolean optional) {
        super(Zone.BATTLEFIELD, effect, optional);
    }

    public AttachedToCreatureTriggeredAbility(final AttachedToCreatureTriggeredAbility ability) {
        super(ability);
    }

    @Override
    public boolean checkEventType(GameEvent event, Game game) {
        return event.getType() == GameEvent.EventType.ATTACHED && event.getSourceId().equals(this.getSourceId());
    }

    @Override
    public boolean checkTrigger(GameEvent event, Game game) {
        Permanent attachedPermanent = game.getPermanent(event.getTargetId());
        return attachedPermanent != null && attachedPermanent.getCardType().contains(CREATURE);
    }

    @Override
    public String getRule() {
        return "As {this} becomes attached to a creature, " + super.getRule();
    }

    @Override
    public AttachedToCreatureTriggeredAbility copy() {
        return new AttachedToCreatureTriggeredAbility(this);
    }
}
