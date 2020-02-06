import React, { Component } from 'react';
import { Form } from 'informed';
import ClientInfo from '../components/ReferralForm/ClientInfo'
import ReferralReason from '../components/ReferralForm/ReferralReason'
import MedicalInfo from '../components/ReferralForm/MedicalInfo'
import ReferrerInfo from '../components/ReferralForm/ReferrerInfo'
import ServiceRequested from '../components/ReferralForm/ServiceRequested'
// import PDFGenerator from '../utils/PDFGenerator'

class ReferralPage extends Component {

  constructor(props) {
    super(props);
    this.state = {};
  }

  referralSubmit(value) {
    console.log("Form: ", JSON.stringify(value));
    // var pdfGen = new PDFGenerator();
    // pdfGen.generatePDF(value, 'TestReferral');
  }


  render() {
    return (
      <div className="container">
        <h2>Referral Form</h2>

        <Form id="referral-form" 
          onSubmit={this.referralSubmit} 
          initialValues={{"sex": "Unspecified", "livesAlone": "No", "aware": "Yes", "homebound": "No", "ccac": "Unsure"}}>
          <ServiceRequested />
          <hr />

          <ClientInfo />
          <hr />

          <ReferralReason />
          <hr />
          
          <MedicalInfo />
          <hr />

          <ReferrerInfo />

          <div className="form-group">
            <button className="btn btn-custom" type="submit">Generate PDF</button>
          </div>
        </Form>
      </div>
    );
  }
}

export default ReferralPage;
