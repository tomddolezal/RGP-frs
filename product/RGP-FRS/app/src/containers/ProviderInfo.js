import React, { Component } from 'react';
import HositalsInfoJSON from '../resources/hospitalinfo.json';

class ProviderInfo extends Component {

 constructor(props){
    super(props);
    this.state = {
      text: "Provider Info",
      hospitals: HositalsInfoJSON.hospitals,
      hospitalID: this.props.match.params.hospitalID
    };
    console.log(HositalsInfoJSON);
  } 

  componentDidMount() {
    let myHosptial = this.getHosptialFromID(this.state.hospitalID);
    if(myHosptial === undefined){
        alert("Hosptal "+this.state.hospitalID+" not found");
    }
    this.setState({
        ...this.state,
        myHospital: myHosptial
    });
  }

  getHosptialFromID = (hospitalID) => {
    let myHospital = undefined;
    this.state.hospitals.forEach((hospital) => {
      if (hospital.UUID === hospitalID){
        myHospital = hospital;
      }      
    });
    return myHospital;
  }

  render() {
    const hosptialName = this.state.myHospital ? this.state.myHospital.hospital : "Not Found";

    return (
      <div className="container">
        <h2>Hospital Information Table</h2>
        <table class="table table-bordered">
            <thead>
                <tr>
                <th scope="col" colspan="2">{hosptialName}</th>
                <th scope="col" colspan="2">Toronto</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                <td scope="row" rowspan="3"><img src="https://www.utosm.com/wp-content/uploads/2015/03/Screen-Shot-2015-05-12-at-11.25.12-AM-300x194.png" alt="Hospital.jpg"/></td>
                <td colspan="3">30 Bond St, Toronto, ON M5B 1W8</td>
                </tr>
                <tr>
                <td colspan="3"><button type="button" class="btn btn-link">Website</button></td>
                </tr>
                <tr>
                <td colspan="3"><button type="button" class="btn btn-link">Write a referral</button></td>
                </tr>
                <tr>
                <th colspan="4">Contact</th>
                </tr>
                <tr>
                <td>Serivce</td>
                <td>Phone</td>
                <td>Fax</td>
                <td>Wait Time</td>
                </tr>
                <tr>
                <td>Inpatient Consulation Team</td>
                <td>416-868-5015</td>
                <td>416-864-5015</td>
                <td>6 Months</td>
                </tr>
                <tr><td colspan="4">Geriatric Outpatient Clinic:</td></tr>
                <tr>
                <td>Geriatric Assessment Clinic</td>
                <td>416-868-5015</td>
                <td>416-864-5015</td>
                <td>2 Weeks</td>
                </tr>
                <tr><td colspan="4">Special Clinics and Programs:</td></tr>
                <tr>
                <td>Memory Disorders Clinic</td>
                <td>416-868-5015</td>
                <td></td>
                <td>6 Months</td>
                </tr>
                <tr>
                <td>Psychogeriatric Clinic</td>
                <td>416-868-5015</td>
                <td>416-864-5015</td>
                <td>6 Months</td>
                </tr>
            </tbody>
        </table>
      </div>
    );
  }
}

export default ProviderInfo;