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
 * File created: 15/07/2020
 */

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CacheRequestList {
    private List<CacheRequest> requests;

    public CacheRequestList() {
        requests = Collections.emptyList();
    }

    public List<CacheRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<CacheRequest> requests) {
        this.requests = requests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheRequestList that = (CacheRequestList) o;
        return Objects.equals(requests, that.requests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requests);
    }

    @Override
    public String toString() {
        return "CacheRequestList{" +
                "requests=" + requests +
                '}';
    }
}
