package dk.dbc.borchk.setup;
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
 * File created: 13/07/2020
 */

import com.hazelcast.cache.CacheStatistics;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CacheStat {
    private static final String DATE_FORMAT = "yyyy-MM-dd@HH:mm:ss.SSS";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private final String name;
    private long size;
    private final float averageGetTime;
    private final float averagePutTime;
    private final float averageRemoveTime;
    private final long cacheEvictions;
    private final long cacheGets;
    private final float cacheHitPercentage;
    private final long cacheHits;
    private final long cacheMisses;
    private final float cacheMissPercentage;
    private final long cachePuts;
    private final long cacheRemovals;
    private final long creationTime;
    private final long lastAccessTime;
    private final long lastUpdateTime;

    public CacheStat(String name, long size, CacheStatistics statistics) {
        this.name = name;
        this.size = size;
        averageGetTime = statistics.getAverageGetTime();
        averagePutTime = statistics.getAveragePutTime();
        averageRemoveTime = statistics.getAverageRemoveTime();
        cacheEvictions = statistics.getCacheEvictions();
        cacheGets = statistics.getCacheGets();
        cacheHitPercentage = statistics.getCacheHitPercentage();
        cacheHits = statistics.getCacheHits();
        cacheMisses = statistics.getCacheMisses();
        cacheMissPercentage = statistics.getCacheMissPercentage();
        cachePuts = statistics.getCachePuts();
        cacheRemovals = statistics.getCacheRemovals();
        creationTime = statistics.getCreationTime();
        lastAccessTime = statistics.getLastAccessTime();
        lastUpdateTime = statistics.getLastUpdateTime();
    }

    public String getName() {
        return name;
    }

    public float getAverageGetTime() {
        return averageGetTime;
    }

    public float getAveragePutTime() {
        return averagePutTime;
    }

    public float getAverageRemoveTime() {
        return averageRemoveTime;
    }

    public long getCacheEvictions() {
        return cacheEvictions;
    }

    public long getCacheGets() {
        return cacheGets;
    }

    public float getCacheHitPercentage() {
        return cacheHitPercentage;
    }

    public long getCacheHits() {
        return cacheHits;
    }

    public long getCacheMisses() {
        return cacheMisses;
    }

    public float getCacheMissPercentage() {
        return cacheMissPercentage;
    }

    public long getCachePuts() {
        return cachePuts;
    }

    public long getCacheRemovals() {
        return cacheRemovals;
    }

    public String getCreationTime() {
        String dd = LocalDateTime.ofInstant(Instant.ofEpochMilli(creationTime), ZoneId.systemDefault()).format(formatter);
        return dd;
    }

    public String getLastAccessTime() {
        String dd = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastAccessTime), ZoneId.systemDefault()).format(formatter);
        return dd;
    }

    public String getLastUpdateTime() {
        String dd = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastUpdateTime), ZoneId.systemDefault()).format(formatter);
        return dd;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
