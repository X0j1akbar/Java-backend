package uz.malga.logisticcompany.entity.enums;

public enum PermissionName {

    ADD_EMPLOYEE("Xodim qo'shish",GroupName.EMPLOYEE_CRUD),
    EDIT_EMPLOYEE("Xodimni Taxrirlash",GroupName.EMPLOYEE_CRUD),
    DELETE_EMPLOYEE("Xodim o'chirish",GroupName.EMPLOYEE_CRUD),
    GET_EMPLOYEE("Xodimlarni olish",GroupName.EMPLOYEE_CRUD),
    ADD_TIR("TIr qo'shish",GroupName.TIR_CRUD),
    EDIT_TIR("Tirni taxrirlash",GroupName.TIR_CRUD),
    DELETE_TIR("TIrni o'chirish",GroupName.TIR_CRUD),
    GET_TIR("Tirlarni Ko'rish",GroupName.TIR_CRUD),
    ADD_DAZVOL("Dazvol Qo'shish",GroupName.DAZVOL_CRUD),
    GET_DAZVOl("Dazvollani ko'rish",GroupName.DAZVOL_CRUD);


    private String nameUz;

    private GroupName groupName;

    PermissionName(String uzName, GroupName groupName) {
        this.nameUz = uzName;
        this.groupName=groupName;
    }
}
