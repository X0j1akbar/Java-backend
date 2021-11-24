package uz.malga.logisticcompany.entity.enums;

public enum GroupName {
    EMPLOYEE_CRUD("Xodimlarni boshqarish"),
    TIR_CRUD("tirlarni boshqarish"),
    DAZVOL_CRUD("Dazvolni boshqarish");


    private String nameUz;

    GroupName(String nameUz) {
        this.nameUz = nameUz;
    }
}
