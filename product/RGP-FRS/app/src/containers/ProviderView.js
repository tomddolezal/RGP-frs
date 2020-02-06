import React, { Component } from 'react';
import HospitalsInfoJSON from '../resources/hospitalinfo.json';

class ProviderView extends Component {

 constructor(props){
    super(props);
    this.state = {
      text: "Provider View",
      hospitals: HospitalsInfoJSON.hospitals,
      serviceID: this.props.match.params.serviceID
    };
     console.log(HospitalsInfoJSON);
  } 

  onButtonClick = (event) => {
    alert("I clicked a button");
  }

 componentDidMount() {
        let myHospital = this.getHospitalFromServiceID(this.state.serviceID);
        let myService = this.getServiceFromServiceID(this.state.serviceID);
        if(myHospital === undefined){
            alert("Hospital with serviceID "+this.state.serviceID+" not found");
        }
         if(myService === undefined){
             alert("Service with serviceID "+this.state.serviceID+" not found");
         }
        this.setState({
            ...this.state,
            myHospital: myHospital,
            myService: myService
        });
    }
 getHospitalFromServiceID = (serviceID) => {
        let myHospital = undefined;
        this.state.hospitals.forEach((hospital) => {
            hospital.services.forEach((service) =>{
                if (service.UUID === serviceID){
                    myHospital = hospital;
                }
            })
        });
        return myHospital;
    }

 getServiceFromServiceID = (serviceID) => {
    let myService = undefined;
    this.state.hospitals.forEach((hospital) => {
        hospital.services.forEach((service) =>{
            if (service.UUID === serviceID){
                myService = service;
            }
        })
    });
    return myService;
}


  render() {
      const HosptialName = this.state.myHospital ? this.state.myHospital.hospital : "Not Found";
      const HosptialAddress = this.state.myHospital ? this.state.myHospital.address : "Not Found";
      const HosptialWeb = this.state.myHospital ? this.state.myHospital.website : "Not Found";

      const ServiceType = this.state.myService ? this.state.myService.type : "Not Found";
      const ServiceName = this.state.myService ? this.state.myService.service : "Not Found";
      const ServiceFax = this.state.myService ? this.state.myService.contact[1] : "Not Found";
      const ServiceTelephone = this.state.myService ? this.state.myService.contact[0] : "Not Found";
      const ServiceWaittime = this.state.myService ? this.state.myService.waittime : "Not Found";
    return (
      <div class="container">
          <br />
          <h5>Hello! <a>{HosptialName}</a></h5>
          <button type="button" class="btn btn-link">Log out</button>

          <table className="table">
              <thead>
              <tr>
                  <th>hospital</th>
                  <th>address</th>
                  <th>website</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                  <td><a>{HosptialName}</a></td>
                  <td><a>{HosptialAddress}</a></td>
                  <td><a>{HosptialWeb}</a></td>
              </tr>
              </tbody>
          </table>

          <table class="table">
            <thead>
              <tr>
                <th>Service Type</th>
                <th>Service Name</th>
                <th>Fax</th>
                <th>Telephone</th>
                <th>Wait time</th>
                <th>Update</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><a>{ServiceType}</a></td>
                <td><a>{ServiceName}</a></td>
                <td><a>{ServiceFax}</a></td>
                <td><a>{ServiceTelephone}</a></td>
                <td><a>{ServiceWaittime}</a></td>
                    <td>
                        <div className="btn-group-vertical">
                    <input type="text" className="form-control" id="newWaitTime" placeholder="Enter wait time" name="time"/>
                    <button type="button" class="btn btn-primary" onClick={this.onButtonClick}>Update</button>
                    </div>
                </td>
              </tr>
            </tbody>
          </table>
          <br />
          <br />
        </div>
    );
  }
}

export default ProviderView;
