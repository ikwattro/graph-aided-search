/*
 * Copyright (c) 2013-2016 GraphAware
 *
 * This file is part of the GraphAware Framework.
 *
 * GraphAware Framework is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package com.graphaware.integration.es.stubs;

import com.graphaware.integration.es.booster.SearchResultCypherBooster;
import com.graphaware.integration.es.annotation.SearchBooster;
import com.graphaware.integration.es.IndexInfo;
import com.graphaware.integration.es.domain.ExternalResult;
import org.elasticsearch.common.settings.Settings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SearchBooster(name = "CypherSearchResultTestBooster")
public class CypherSearchResultTestBooster extends SearchResultCypherBooster {

    public CypherSearchResultTestBooster(Settings settings, IndexInfo indexInfo) {
        super(settings, indexInfo);
    }

    @Override
    protected Map<String, ExternalResult> getExternalResults(Set<String> keySet) {

        Map<String, ExternalResult> results = new HashMap<>();
        for (int i = 0; i < 1000; ++i) {
            results.put(String.valueOf(i), new ExternalResult(String.valueOf(i), 1000.0f*i));
        }
        return results;
    }
}
