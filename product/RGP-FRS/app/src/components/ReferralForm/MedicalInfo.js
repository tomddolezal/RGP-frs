import React, { Component } from 'react';
import { TextArea } from 'informed';

class MedicalInfo extends Component {

  concern() {
    return (
      <div className="form-group">
        <h6>Main Concern(s) to be addressed</h6>
        <TextArea className="form-control" field="main-concern" id="textarea-main-concern" required />
      </div>
    );
  }

  history() {
    return (
      <div className="form-group">
        <h6>Medical History</h6>
        <TextArea className="form-control" field="medical-history" id="textarea-medical-history" required />
      </div>
    );

  }

  render() {
    return (
      <div>
        <h3>Medical Information</h3>

        {/* Main Concern */}
        {this.concern()}

        {/* Medical History */}
        {this.history()}

      </div>
    );
  }


}

export default MedicalInfo;
