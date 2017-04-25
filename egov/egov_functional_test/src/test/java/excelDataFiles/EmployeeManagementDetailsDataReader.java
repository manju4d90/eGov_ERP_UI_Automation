package excelDataFiles;

import builders.employeeManagement.AssignmentDetailsBuilder;
import builders.employeeManagement.EmployeeDetailsBuilder;
import entities.employeeManagement.AssignmentDetails;
import entities.employeeManagement.EmployeeDetails;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class EmployeeManagementDetailsDataReader extends ExcelReader {

    Sheet assignmentDetailsSheet;
    Sheet employeeDetailsSheet;

    public EmployeeManagementDetailsDataReader(String testData) {
        super(testData);
        assignmentDetailsSheet = workbook.getSheet("assignmentDetails");
        employeeDetailsSheet = workbook.getSheet("employeeDetails");
    }

    public AssignmentDetails getAssignmentDetails(String dataName) {
        Row dataRow = readDataRow(assignmentDetailsSheet, dataName);

        String isPrimary = getCellData(assignmentDetailsSheet, dataRow, "IsPrimary").getStringCellValue();
        String fromDate = convertNumericToString(assignmentDetailsSheet, dataRow, "FromDate");
        String toDate = convertNumericToString(assignmentDetailsSheet, dataRow, "ToDate");
        String mainDepartment = getCellData(assignmentDetailsSheet, dataRow, "MainDepartment").getStringCellValue();
        String designation = getCellData(assignmentDetailsSheet, dataRow, "Designation").getStringCellValue();
        String position = getCellData(assignmentDetailsSheet, dataRow, "Position").getStringCellValue();

        return new AssignmentDetailsBuilder()
                .withIsPrimary(isPrimary)
                .withFromDate(fromDate)
                .withToDate(toDate)
                .withDepartment(mainDepartment)
                .withDesignation(designation)
                .withPosition(position)
                .build();
    }

    public EmployeeDetails getEmployeeDetails(String dataName){
        Row dataRow = readDataRow(employeeDetailsSheet, dataName);

        String employeeType = getCellData(employeeDetailsSheet,dataRow,"EmployeeType").getStringCellValue();
        String status = getCellData(employeeDetailsSheet,dataRow,"Status").getStringCellValue();
        String dateOfBirth = getCellData(employeeDetailsSheet,dataRow,"DateOfBirth").getStringCellValue();
        String gender = getCellData(employeeDetailsSheet,dataRow,"Gender").getStringCellValue();
        String martialStatus = getCellData(employeeDetailsSheet,dataRow,"MaritalStatus").getStringCellValue();
        String userName = getCellData(employeeDetailsSheet,dataRow,"UserName").getStringCellValue();
        String isUserActive = getCellData(employeeDetailsSheet,dataRow,"IsUserActive").getStringCellValue();
        String mobileNumber = getCellData(employeeDetailsSheet,dataRow,"Mobile").getStringCellValue();
        String permanentAddress = getCellData(employeeDetailsSheet,dataRow,"PermanentAddress").getStringCellValue();
        String permanentCity = getCellData(employeeDetailsSheet,dataRow,"City").getStringCellValue();
        String permanentPincode = getCellData(employeeDetailsSheet,dataRow,"PinCode").getStringCellValue();
        String dataOfJoining = getCellData(employeeDetailsSheet,dataRow,"DateOfAppointment").getStringCellValue();

        return new EmployeeDetailsBuilder()
                .withEmployeeType(employeeType)
                .withStatus(status)
                .withDateOfBirth(dateOfBirth)
                .withGender(gender)
                .withMaritalStatus(martialStatus)
                .withUserName(userName)
                .withIsUserActive(isUserActive)
                .withMobileNumber(mobileNumber)
                .withPermanentAddress(permanentAddress)
                .withPermanentCity(permanentCity)
                .withPermanentPincode(permanentPincode)
                .withDateOfAppointment(dataOfJoining)
                .build();
        }
}
