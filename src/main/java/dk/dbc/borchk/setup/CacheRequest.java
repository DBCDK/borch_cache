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

public class CacheRequest {
    private String requestName;
    private String agencyFileName = "";
    private String request;
    private List<String> agencies = Collections.emptyList();

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getAgencyFileName() {
        return agencyFileName;
    }

    public void setAgencyFileName(String agencyFileName) {
        this.agencyFileName = agencyFileName;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public List<String> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<String> agencies) {
        this.agencies = agencies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheRequest that = (CacheRequest) o;
        return Objects.equals(requestName, that.requestName) &&
                Objects.equals(agencyFileName, that.agencyFileName) &&
                Objects.equals(request, that.request);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestName, agencyFileName, request);
    }

    @Override
    public String toString() {
        return "CacheRequest{" +
                "requestName='" + requestName + '\'' +
                ", agencyFile='" + agencyFileName + '\'' +
                ", request='" + request + '\'' +
                '}';
    }
}
