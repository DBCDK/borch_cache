/*
 * Copyright (C) 2020 DBC A/S (http://dbc.dk/)
 *
 * This is part of openagency
 *
 * openagency is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * openagency is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * File created: 03/07/2020
 */

package dk.dbc.borchk.setup;

import com.hazelcast.cache.CacheStatistics;
import com.hazelcast.cache.HazelcastCacheManager;
import com.hazelcast.cache.ICache;
import com.hazelcast.cache.impl.HazelcastServerCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
@Startup
@Lock(LockType.READ)
public class HazelcastSetup {
    private static final Logger LOGGER = LoggerFactory.getLogger(HazelcastSetup.class);

    private CacheManager manager;
    private HazelcastCacheManager hazelcastManager;

    private enum HazelcastSetupStates {BEFORE_STARTED, STARTED}

    private static HazelcastSetupStates state = HazelcastSetupStates.BEFORE_STARTED;
    private static final List<String> CACHE_NAMES = Collections.singletonList("BORCHK_CACHE");

    public boolean isReady() {
        boolean isReady = (state == HazelcastSetupStates.STARTED);
        LOGGER.debug("HazelcastSetup isReady={}", isReady);
        return isReady;
    }

    public CacheManager getManager() {
        return manager;
    }

    @PostConstruct
    private void initializeHazelcastSetup() {
        LOGGER.trace("Enter initializeHazelcastSetup -->");
        state = HazelcastSetupStates.BEFORE_STARTED;
        try {
            Context ctx = new InitialContext();
            manager = (CacheManager) ctx.lookup("payara/CacheManager");
            hazelcastManager = manager.unwrap(HazelcastServerCacheManager.class);
            createCaches();
        } catch (NamingException ex) {
            LOGGER.error("Error configuring JCache: {}", ex.getMessage());
            LOGGER.debug("Error configuring JCache", ex);
            throw new EJBException("Error configuring JCache", ex);
        } finally {
            state = HazelcastSetupStates.STARTED;
            LOGGER.trace("Exit initializeHazelcastSetup <--");
        }
    }

    @PreDestroy
    public void terminateHazelcastSetup() {
        LOGGER.trace("Enter terminateHazelcastSetup -->");
        LOGGER.debug("Shut down in progress");
        LOGGER.trace("Exit terminateHazelcastSetup <--");
    }

    public List<String> getCacheNames() {
        Iterable<String> cahcenames = hazelcastManager.getCacheNames();
        return StreamSupport.stream(cahcenames.spliterator(), false).collect(Collectors.toList());
    }

    public CacheStat getCacheStatistics(String cacheName) {
        Cache c = hazelcastManager.getCache(cacheName);
        ICache unwrappedCache = (ICache) c.unwrap(ICache.class);
        CacheStatistics stats = unwrappedCache.getLocalCacheStatistics();

        return new CacheStat(cacheName, unwrappedCache.size(), stats);
    }

    public void deleteCache() {
        List<String> cahcenames = getCacheNames();
        for (String name : cahcenames) {
            Cache c = hazelcastManager.getCache(name);
            ICache unwrappedCache = (ICache) c.unwrap(ICache.class);
            unwrappedCache.destroy();
        }
        createCaches();
    }

    private void createCaches() {
        CACHE_NAMES.forEach(name -> {
            hazelcastManager.getCache(name); // Forces Hazelcast to initialize the cache.
            hazelcastManager.enableStatistics(name, true);
        });
    }
}
