/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.magic2013;

import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Outcome;
import mage.Constants.Rarity;
import mage.Constants.TargetController;
import mage.Constants.Zone;
import mage.abilities.Ability;
import mage.abilities.dynamicvalue.common.PermanentsOnBattlefieldCount;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Card;
import mage.cards.CardImpl;
import mage.filter.common.FilterBasicLandCard;
import mage.filter.common.FilterLandPermanent;
import mage.filter.predicate.permanent.ControllerPredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;
import mage.target.common.TargetCardInLibrary;

/**
 *
 * @author North
 */
public class BoundlessRealms extends CardImpl<BoundlessRealms> {

    public BoundlessRealms(UUID ownerId) {
        super(ownerId, 162, "Boundless Realms", Rarity.RARE, new CardType[]{CardType.SORCERY}, "{6}{G}");
        this.expansionSetCode = "M13";

        this.color.setGreen(true);

        // Search your library for up to X basic land cards, where X is the number of lands you control, and put them onto the battlefield tapped. Then shuffle your library.
        this.getSpellAbility().addEffect(new BoundlessRealmsEffect());
    }

    public BoundlessRealms(final BoundlessRealms card) {
        super(card);
    }

    @Override
    public BoundlessRealms copy() {
        return new BoundlessRealms(this);
    }
}

class BoundlessRealmsEffect extends OneShotEffect<BoundlessRealmsEffect> {

    public BoundlessRealmsEffect() {
        super(Outcome.Benefit);
        this.staticText = "Search your library for up to X basic land cards, where X is the number of lands you control, and put them onto the battlefield tapped. Then shuffle your library.";
    }

    public BoundlessRealmsEffect(final BoundlessRealmsEffect effect) {
        super(effect);
    }

    @Override
    public BoundlessRealmsEffect copy() {
        return new BoundlessRealmsEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        FilterLandPermanent filter = new FilterLandPermanent();
        filter.add(new ControllerPredicate(TargetController.YOU));

        int amount = new PermanentsOnBattlefieldCount(filter).calculate(game, source);
        TargetCardInLibrary target = new TargetCardInLibrary(0, amount, new FilterBasicLandCard());

        Player player = game.getPlayer(source.getControllerId());
        if (player == null) {
            return false;
        }

        if (player.searchLibrary(target, game)) {
            for (UUID cardId : target.getTargets()) {
                Card card = player.getLibrary().getCard(cardId, game);
                if (card != null) {
                    if (card.putOntoBattlefield(game, Zone.LIBRARY, source.getId(), source.getControllerId())) {
                        Permanent permanent = game.getPermanent(card.getId());
                        if (permanent != null) {
                            permanent.setTapped(true);
                        }
                    }
                }
            }
        }

        player.shuffleLibrary(game);
        return true;
    }
}
