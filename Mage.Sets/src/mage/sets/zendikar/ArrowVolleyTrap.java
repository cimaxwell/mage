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
package mage.sets.zendikar;

import java.util.List;
import java.util.UUID;
import mage.Constants.CardType;
import mage.Constants.Rarity;
import mage.abilities.Ability;
import mage.abilities.costs.AlternativeCostImpl;
import mage.abilities.costs.mana.ManaCostsImpl;
import mage.abilities.effects.common.DamageMultiEffect;
import mage.cards.CardImpl;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.permanent.AttackingPredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.target.common.TargetCreaturePermanentAmount;


/**
 * @author LevelX2
 */
public class ArrowVolleyTrap extends CardImpl<ArrowVolleyTrap> {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent();

    static {
        filter.add(new AttackingPredicate());
    }

    public ArrowVolleyTrap(UUID ownerId) {
        super(ownerId, 2, "Arrow Volley Trap", Rarity.UNCOMMON, new CardType[]{CardType.INSTANT}, "{3}{W}{W}");
        this.expansionSetCode = "ZEN";
        this.color.setWhite(true);

        // If four or more creatures are attacking, you may pay {1}{W} rather than pay Arrow Volley Trap's mana cost.
        this.getSpellAbility().addAlternativeCost(new ArrowVolleyTrapAlternativeCost());

        // Arrow Volley Trap deals 5 damage divided as you choose among any number of target attacking creatures.
        this.getSpellAbility().addEffect(new DamageMultiEffect(5));
        this.getSpellAbility().addTarget(new TargetCreaturePermanentAmount(5, filter));

    }

    public ArrowVolleyTrap(final ArrowVolleyTrap card) {
        super(card);
    }

    @Override
    public ArrowVolleyTrap copy() {
        return new ArrowVolleyTrap(this);
    }
}

class ArrowVolleyTrapAlternativeCost extends AlternativeCostImpl<ArrowVolleyTrapAlternativeCost> {

    public ArrowVolleyTrapAlternativeCost() {
        super("you may pay {1}{W} rather than pay Arrow Volley Trap's mana cost");
        this.add(new ManaCostsImpl("{1}{W}"));
    }

    public ArrowVolleyTrapAlternativeCost(final ArrowVolleyTrapAlternativeCost cost) {
        super(cost);
    }

    @Override
    public ArrowVolleyTrapAlternativeCost copy() {
        return new ArrowVolleyTrapAlternativeCost(this);
    }

    @Override
    public boolean isAvailable(Game game, Ability source) {
        if (game.getCombat().getAttackers().size() > 3) {
            return true;
        }
        return false;
    }

    @Override
    public String getText() {
        return "If four or more creatures are attacking, you may pay {1}{W} rather than pay Arrow Volley Trap's mana cost";
    }
}