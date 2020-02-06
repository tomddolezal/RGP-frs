import * as jsPDF from 'jspdf'
// Documentation: https://rawgit.com/MrRio/jsPDF/master/docs/jsPDF.html

/*
{
   "surname":"LastName",
   "first-name":"FirstName",
   "sex":"M",
   "address":{
      "street-address":"6 Hoskin Ave",
      "apt-no":"123",
      "city":"Toronto",
      "province":"ON",
      "postal-code":"M5S1H8"
   },
   "telephone":"4167774444",
   "livesAlone":"No",
   "marital-status":"single",
   "health-card-number":"4444-999-000-111",
   "health-card-version":"YM",
   "dob":"1911-04-29",
   "alt-contact-name":"AltContact",
   "alt-contact-relationship":"Spouse",
   "alt-contact-telephone":"6474446666",
   "contact-person":"ContactPerson",
   "translator":true,
   "translator-lang":"Inuktitut",
   "aware":"Yes",
   "homebound":"No",
   "ccac":"Yes",
   "ccac-name":"CaseMgr",
   "ccac-tel":"4376664444"
}
*/

class PDFGenerator {

  generatePDF(formValues, filename) {
    var doc = new jsPDF({
      orientation: 'portrait',
      unit: 'in',
      format: [11, 8]
    });


    doc.setFontSize(12);
    // write Hello world at x= 1 inch, y = 1 inch (from top left
    // write firstname and surname on the next line
    doc.text(['Hello world!', formValues['first-name'] + " " + formValues['surname']], 1, 1);

    doc.text([
      formValues['address']['street-address'] + " " + formValues['address']['apt-no'],
      formValues['address']['city'] + ", " + formValues['address']['province'],
      formValues['address']['postal-code']
    ], 2, 1);

    doc.save(filename + '.pdf');
  }

}

export default PDFGenerator;
