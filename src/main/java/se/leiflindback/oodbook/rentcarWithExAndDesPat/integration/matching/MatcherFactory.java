/*
 * Copyright (c) 2016, Leif Lindbäck
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials provided
 * with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package se.leiflindback.oodbook.rentcarWithExAndDesPat.integration.matching;

/**
 * A Singleton that creates instances of the algorithm used for matching a customer's wishes against
 * available cars. All such instances must implement <code>Matcher</code>.
 */
public class MatcherFactory {
    private static final MatcherFactory INSTANCE = new MatcherFactory();
    private CompositeMatch matchers = new CompositeMatch();

    private MatcherFactory() {
    }

    /**
     * @return the only existing instance of this singleton.
     */
    public static MatcherFactory getFactory() {
        return INSTANCE;
    }

    /**
     * Returns a <code>Matcher</code> performing the default matching algorithm. The class name of
     * the default <code>Matcher</code> implementation is read from the system property
     * <code>se.leiflindback.rentcar.matcher.classname</code>
     *
     * @return The default matcher
     * @throws ClassNotFoundException If unable to load the default matcher class.
     * @throws InstantiationException If unable to instantiate the default matcher class.
     * @throws IllegalAccessException If unable to instantiate the default matcher class.
     */
    public Matcher getDefaultMatcher() {
        PromotingMatch promotingMatch = new PromotingMatch();
        promotingMatch.setCarToPromote("ABC123");
        matchers.addMatcher(promotingMatch);
        matchers.addMatcher(new WildCardMatch());
        return matchers;
    }
}
