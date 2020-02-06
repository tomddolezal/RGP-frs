## database_schema

***UserAccount***(username, password, name, identity, tel, fax)

Hospital(
name, 
masterNumber, 
outreachServiceName, 
outreachServiceTel, 
outreachServiceFax, 
outreachServiceWaitTime,
geriatricDayName, 
geriatricDayTel, 
geriatricDayFax, 
outpatientClinicsName, 
outpatientClinicsTel, 
outpatientClinicsFax,
outpatientClinicsWaitTime, 
otherOutpatientServiceName, 
otherOutpatientServiceTel, 
otherOutpatientServiceFax, 
otherOutpatientServiceWaitTime
)

Patient(
firstName, 
lastName, 
gender, 
address, 
city, 
postalCode, 
healthCardNumber, 
versionCode, 
tel, 
liveAlone, 
maritalStatus, 
DoB, 
alternateContactName, 
alternateContactRelationship, 
alternateContactTel, 
translatorRequred, 
translatorLanguage, 
contactPerson, 
awareOfReferal, 
homebound, 
CCACInvolved, 
caseManagerName, 
caseManagerTel, 
familyMDName, 
familyMDTel, 
familyMDFax
)

-	Patient{contactPerson} $\subseteq$ UserAccount{name}
-	Hospital{name} $\subseteq$ UserAccount{name}

* UserAccount: 
    * username is unique; 
    * name is the real name of the doctor or the hospital; 
    * password is the hashed password; 
    * identity coule be doctor, hospital or admin.
* Hospital:
    * name and masterNumber combined to be the key;
    * masterNumber is an identification number for all hospitals or health related institutions in Canada;
* Patient:
    * firstName, lastName and DoB (date of birth) combined to be key;
    * contackPerson is the referring doctor;
    * alternateContact* is the information about the alternate contact person (not the doctor nor the patient)
