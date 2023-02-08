package com.climbing_log.enums;

public enum Grade {

    //#region ROUTES
    R0("5.4"),
    R1("5.5"),
    R2("5.6"),
    R3("5.7"),
    R4("5.8"),
    R5("5.9"),
    R6("5.10a"),
    R7("5.10b"),
    R8("5.10c"),
    R9("5.10d"),
    R10("5.11a"),
    R11("5.11b"),
    R12("5.11c"),
    R13("5.11d"),
    R14("5.12a"),
    R15("5.12b"),
    R16("5.12c"),
    R17("5.12d"),
    R18("5.13a"),
    R19("5.13b"),
    R20("5.13c"),
    R21("5.13d"),
    R22("5.14a"),
    R23("5.14b"),
    R24("5.14c"),
    R25("5.14d"),
    R26("5.15a"),
    R27("5.15b"),
    R28("5.15c"),
    R29("5.15d"),
     //#endregion
    //#region BOULDERS
    B0("V0"),
    B1("V1"),
    B2("V2"),
    B3("V3"),
    B4("V4"),
    B5("V5"),
    B6("V6"),
    B7("V7"),
    B8("V8"),
    B9("V9"),
    B10("V10"),
    B11("V11"),
    B12("V12"),
    B13("V13"),
    B14("V14"),
    B15("V15"),
    B16("V16");
    //#endregion
    private final String grade;

    private Grade(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return this.grade;
    }

    public boolean isRoute() {
        return this.grade.charAt(0) == '5';
    }
}
