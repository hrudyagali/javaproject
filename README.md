NAME:G.HRUDYA
ROLLNO:100523729020
AIML BE 3 RD SEM
 **JAVA MINI PROJECT**
 
 **I. Use Case:** 
 1. inputs to the program are either the DOB of a person OR age of the person.
 2. Option DOB: determine the age of the person as of today OR
 with reference to a specific date
 3. Option AGE: if age is provided then it is assumed that it is
 with reference to today, then, determine the DOB.

 **II. FORMAT:**
 1. The Date format could be
 i) YYYYdlcMMdlcDD (international style),
 ii) DDdlcMMdlcYYYY (indian style) or
 iii) MMdlcDDdlcYYYY (USA)
 
 **2. Acceptable DOB format:**
 "AGE=YYYYdlcMMdlcDD"
 age format: "AGE=00YYdlcMMdlcDD"
 where dlc--> Delimiter character

 **III. Explanation:**
 1. dlc is one of the tokens: '-', '/' , or '.'
 For example, 20Feb,2001 in the format of
 DDdlcMMdlcYYYY →20-02-2001 where dlc ='-'
 2. Acceptable birth date input : "DOB=27-02-2001"
 3. Acceptable Age input : "AGE=27-02-2001"
 Catch errors / Exceptions if any incorrect date is provided then,
 refuse to process further and print out the reason.
 For example, 29-02-2023 , 30-13-2022 etc., are incorrect date entries.
 Entire use case is based on only one single Date Format that is selected by the
 user.

**IV. Program inputs in the order:**
 The program is executed using the Command
 Line-Arguments. 1st param, 2nd param, 3rd param, 4th param
 1st param: "DOB=27-02-2001", or "AGE=22dlc04dlc20"
 2nd param: current date or reference date.
 ( enter either the current date or specific reference date)
 3rd param: Date format, DDdlcMMdlcYYYY
 4th param: dlc
 
**V. results:**
 if inputs are
 1) "DOB=28-02-2001", "27-02-2024", "DD-MM-YYYY", "-"
 print age is 23 years, 0 months, 0days
 2) for 19 yrs , 10 months and 27 days aged person,
 "AGE=27-10-0019", "27-10-2024", "DD-MM-YYYY", "-"
 print DOB

Examples:

1.
input:
java AgeCalculator "DOB=15-04-1990" "15-11-2024" "DD-MM-YYYY" "-"
output:
Age is 34 years, 7 months, 0 days

2.
input:
java AgeCalculator "DOB=15-15-1990" "15-11-2024" "DD-MM-YYYY" "-"
output:
Error: Invalid DOB.





