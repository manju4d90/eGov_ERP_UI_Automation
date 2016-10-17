/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2015>  eGovernments Foundation
 *
 *     The updated version of eGov suite of products as by eGovernments Foundation
 *     is available at http://www.egovernments.org
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program. If not, see http://www.gnu.org/licenses/ or
 *     http://www.gnu.org/licenses/gpl.html .
 *
 *     In addition to the terms of the GPL license to be adhered to in using this
 *     program, the following additional terms are to be complied with:
 *
 *         1) All versions of this program, verbatim or modified must carry this
 *            Legal Notice.
 *
 *         2) Any misrepresentation of the origin of the material is prohibited. It
 *            is required that all modified versions of this material be marked in
 *            reasonable ways as different from the original version.
 *
 *         3) This license does not grant any rights to any user of the program
 *            with regards to rights under trademark law for use of the trade names
 *            or trademarks of eGovernments Foundation.
 *
 *   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
 */
package org.egov.works.autonumber.impl;

import java.io.Serializable;

import org.egov.commons.CFinancialYear;
import org.egov.infra.persistence.utils.ApplicationSequenceNumberGenerator;
import org.egov.works.autonumber.ContractorMBNumberGenerator;
import org.egov.works.contractorportal.entity.ContractorMBHeader;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractorMBNumberGeneratorImpl implements ContractorMBNumberGenerator {

    private static final String CONTRACTOR_MB_NUMBER_SEQ_PREFIX = "SEQ_CONTRACTOR_MB_NUMBER";

    @Autowired
    private ApplicationSequenceNumberGenerator applicationSequenceNumberGenerator;

    @Override
    public String getNextNumber(final ContractorMBHeader contractorMBHeader, final CFinancialYear financialYear) {
        final String financialYearRange = financialYear.getFinYearRange();
        final String finYearRange[] = financialYearRange.split("-");
        final String sequenceName = CONTRACTOR_MB_NUMBER_SEQ_PREFIX + "_" + finYearRange[0] + "_" + finYearRange[1];
        Serializable sequenceNumber;
        final DateTime dateTime = new DateTime();
        sequenceNumber = applicationSequenceNumberGenerator.getNextSequence(sequenceName);
        return String.format("CR/MB/%05d/%02d/%s", sequenceNumber, Integer.parseInt(dateTime.toString("MM")), financialYearRange);
    }
}