package builders.propertyTax.billingServices.businessServiceMaster;

import builders.propertyTax.billingServices.AuditDetailsBuilder;
import entities.requests.propertyTax.billingServices.AuditDetails;
import entities.requests.propertyTax.billingServices.businessServiceMaster.BusinessServiceDetails;

import static data.ConstantData.serviceName;
import static data.ConstantData.tenantId;

public class BusinessServiceDetailsBuilder {

    BusinessServiceDetails details = new BusinessServiceDetails();

    String[] modesNotAllowed = new String[1];

    AuditDetails auditDetails = new AuditDetailsBuilder().build();

    public BusinessServiceDetailsBuilder(){
        details.setId(0);
        details.setTenantId(tenantId);
        details.setBusinessService(serviceName);
        details.setCallBackApportionURL("/pt-property/properties/_apportion");
        details.setPartPaymentAllowed(true);
        details.setCallBackForApportioning(true);
        details.setCollectionModesNotAllowed(modesNotAllowed);
        details.setAuditDetails(auditDetails);
    }

    public BusinessServiceDetails build(){
        return details;
    }
}
