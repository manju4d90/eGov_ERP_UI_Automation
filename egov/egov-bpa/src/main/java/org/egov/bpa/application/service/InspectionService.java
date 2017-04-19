/*
 * eGov suite of products aim to improve the internal efficiency,transparency,
 *    accountability and the service delivery of the government  organizations.
 *
 *     Copyright (C) <2017>  eGovernments Foundation
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
package org.egov.bpa.application.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.egov.bpa.application.autonumber.InspectionNumberGenerator;
import org.egov.bpa.application.entity.BpaApplication;
import org.egov.bpa.application.entity.CheckListDetail;
import org.egov.bpa.application.entity.Docket;
import org.egov.bpa.application.entity.DocketDetail;
import org.egov.bpa.application.entity.Inspection;
import org.egov.bpa.application.repository.InspectionRepository;
import org.egov.infra.admin.master.entity.User;
import org.egov.infra.admin.master.service.UserService;
import org.egov.infra.config.core.ApplicationThreadLocals;
import org.egov.infra.utils.autonumber.AutonumberServiceBeanResolver;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class InspectionService {

    @Autowired
    private CheckListDetailService checkListDetailService;
    @Autowired
    private AutonumberServiceBeanResolver beanResolver;

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private InspectionRepository inspectionRepository;

    public Session getCurrentSession() {
        return entityManager.unwrap(Session.class);
    }

    @Transactional
    public Inspection save(final Inspection inspection, final BpaApplication application) {
        User currentUser = null;
        if (inspection.getId() == null) {
            if (ApplicationThreadLocals.getUserId() != null)
                currentUser = userService.getUserById(ApplicationThreadLocals.getUserId());

            inspection.setInspectedBy(currentUser);

            inspection.setInspectionNumber(generateInspectionnumber());
        }
        if (inspection.getInspectionDate() == null)
            inspection.setInspectionDate(new Date());
        inspection.setApplication(application);
        inspection.getDocket().get(0).setInspection(inspection);
        buildDocketDetails(inspection.getDocket().get(0));
        return inspectionRepository.save(inspection);
    }

    public List<Inspection> findByIdOrderByIdAsc(final Long id) {
        return inspectionRepository.findByIdOrderByIdAsc(id);
    }

    public List<Inspection> findByBpaApplicationOrderByIdAsc(final BpaApplication application) {
        return inspectionRepository.findByApplicationOrderByIdDesc(application);
    }

    public String generateInspectionnumber() {
        final InspectionNumberGenerator inspectionNUmber = beanResolver
                .getAutoNumberServiceFor(InspectionNumberGenerator.class);
        return inspectionNUmber.generateInspectionNumber("INSP");
    }

    public Docket buildDocketDetails(final Docket docket) {
        for (final DocketDetail dd : docket.getDocketDetail()) {
            final CheckListDetail checkdet = checkListDetailService.findOne(dd.getCheckListDetail().getId());
            dd.setCheckListDetail(checkdet);
            dd.setDocket(docket);
        }
        return docket;

    }

    public List<DocketDetail> prepareDocketDetailList(final BpaApplication application) {
        List<CheckListDetail> inspectionCheckList;
        final Criteria criteria = getCheckListByServiceAndType(application.getServiceType().getId(), "INSPECTION");
        inspectionCheckList = criteria.list();
        final List<DocketDetail> docketTempList = new ArrayList<>();
        for (final CheckListDetail checkDet : inspectionCheckList)
            setDocketDetList(docketTempList, checkDet);
        return docketTempList;
    }

    public Criteria getCheckListByServiceAndType(final Long serviceTypeId, final String checkListTypeVal) {

        final Criteria checkListDet = getCurrentSession().createCriteria(CheckListDetail.class, "checklistdet");
        checkListDet.createAlias("checklistdet.checkList", "checkList");
        checkListDet.createAlias("checkList.serviceType", "servicetype");
        checkListDet.add(Restrictions.eq("servicetype.id", serviceTypeId));
        checkListDet.add(Restrictions.eq("checkList.checklistType", checkListTypeVal));
        return checkListDet;
    }

    public void setDocketDetList(final List<DocketDetail> docketTempList, final CheckListDetail checkDet) {
        final DocketDetail docdet = new DocketDetail();
        docdet.setCheckListDetail(checkDet);
        docketTempList.add(docdet);
    }

}
